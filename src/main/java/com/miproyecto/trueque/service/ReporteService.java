package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.ReporteRequest;
import com.miproyecto.trueque.dto.ReporteResponse;
import com.miproyecto.trueque.model.DiasHoras;
import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.model.Reporte;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import com.miproyecto.trueque.repository.DiasHorasRepository;
import com.miproyecto.trueque.repository.EmployeeRepository;
import com.miproyecto.trueque.repository.ReporteRepository;
import com.miproyecto.trueque.repository.catalog.PeriodosCreadosEmpleadoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReporteService {
    private final EmployeeRepository empleadoRepository;
    private final DiasHorasRepository diasHorasRepository;
    private final PeriodosCreadosEmpleadoRepository periodosCreadosEmpleadoRepository;
    private final ReporteRepository reporteGeneradoRepository;

    public void generarReporteAutomaticoAlCerrarPeriodo(Long periodoId) {
        PeriodoPago periodo = periodosCreadosEmpleadoRepository.findById(periodoId)
                .orElseThrow(() -> new RuntimeException("Periodo no encontrado"));

        if (!periodo.isEstado() || periodo.getFechaFin().isAfter(LocalDate.now())) {
            log.warn("El periodo no está cerrado o su fecha aún no ha finalizado.");
            return;
        }

        if (reporteGeneradoRepository.existsByPeriodoPago_Id(periodoId)) {
            log.info("Reporte ya generado previamente para el periodo con ID {}", periodoId);
            return;
        }

        List<Employee> empleados = empleadoRepository.findAll();

        for (Employee empleado : empleados) {
            List<DiasHoras> diasEmpleado = diasHorasRepository.findAllByEmpleadoIdAndFechaBetween(
                    empleado.getId(), periodo.getFechaInicio(), periodo.getFechaFin());

            Map<Integer, List<DiasHoras>> diasPorSemana = diasEmpleado.stream()
                    .collect(Collectors.groupingBy(dh -> dh.getFecha().get(WeekFields.ISO.weekOfWeekBasedYear())));

            double totalHorasExtras = diasPorSemana.values().stream()
                    .mapToDouble(this::calcularHorasExtrasPorSemana)
                    .sum();

            long diasTrabajados = diasEmpleado.stream()
                    .filter(dh -> dh.getHorasTrabajadas() > 0)
                    .count();

            long diasDescansoTrabajados = diasEmpleado.stream()
                    .filter(dh -> Boolean.TRUE.equals(dh.getEsDiaDescanso()))
                    .count();

            long retardo = contarInconsistencias(diasEmpleado, 1L);
            long falta = contarInconsistencias(diasEmpleado, 2L);
            long permiso = contarInconsistencias(diasEmpleado, 3L);
            long incapacidad = contarInconsistencias(diasEmpleado, 4L);

            Reporte reporte = new Reporte();
            reporte.setCodigoEmpleado(empleado.getCodigoEmpleado());
            reporte.setNombreEmpleado(empleado.getNombreCompleto());
            reporte.setDiasTrabajados((int) diasTrabajados);
            reporte.setFaltas((int) falta);
            reporte.setRetardos((int) retardo);
            reporte.setSalidasTempranas((int) permiso);
            reporte.setHorasExtras(BigDecimal.valueOf(totalHorasExtras));
            reporte.setDiasDescansoTrabajados((int) diasDescansoTrabajados);
            reporte.setPeriodoPago(periodo);

            reporteGeneradoRepository.save(reporte);
        }

        log.info("Reporte generado exitosamente para el periodo con ID {}", periodoId);
    }


    public List<ReporteResponse> generarReporte(ReporteRequest request) {
        List<ReporteResponse> reporteFinal = new ArrayList<>();
        log.info("Iniciando generación de reporte con parámetros: {}", request);

        try {
            if (request.getTipoPeriodoId() == null) {
                log.error("Tipo de periodo es null en la solicitud");
                throw new IllegalArgumentException("El tipo de periodo es obligatorio.");
            }

            List<PeriodoPago> periodos = new ArrayList<>();

            if (request.getPeriodoId() != null) {
                log.info("Buscando periodo por ID: {}", request.getPeriodoId());
                PeriodoPago periodo = periodosCreadosEmpleadoRepository.findById(request.getPeriodoId())
                        .orElseThrow(() -> {
                            String msg = "No se encontró el periodo con ID: " + request.getPeriodoId();
                            log.error(msg);
                            return new IllegalArgumentException(msg);
                        });

                if (!periodo.getTipoPeriodoEmpleado().getId().equals(request.getTipoPeriodoId())) {
                    String msg = "El tipo de periodo no coincide con el periodo seleccionado.";
                    log.error(msg + " Esperado: {}, Encontrado: {}", request.getTipoPeriodoId(), periodo.getTipoPeriodoEmpleado().getId());
                    throw new IllegalArgumentException(msg);
                }

                periodos.add(periodo);
            } else {
                log.info("Buscando periodos por tipoPeriodoId: {}", request.getTipoPeriodoId());
                periodos = periodosCreadosEmpleadoRepository.findByTipoPeriodoEmpleado_Id(request.getTipoPeriodoId());
            }

            if (periodos.isEmpty()) {
                log.warn("No se encontraron periodos con el tipoPeriodoId: {}", request.getTipoPeriodoId());
                return reporteFinal;
            }

            List<Employee> empleados = empleadoRepository.findByTipoPeriodo_Id(request.getTipoPeriodoId());

            log.info("Se encontraron {} empleados", empleados.size());

            for (PeriodoPago periodo : periodos) {
                LocalDate fechaInicio = periodo.getFechaInicio();
                LocalDate fechaFin = periodo.getFechaFin();
                log.info("Procesando periodo ID={} desde {} hasta {}", periodo.getId(), fechaInicio, fechaFin);

                for (Employee empleado : empleados) {
                    try {
                        log.debug("Procesando empleado ID={}, Código={}", empleado.getId(), empleado.getCodigoEmpleado());
                        List<DiasHoras> diasEmpleado = diasHorasRepository.findAllByEmpleadoIdAndFechaBetween(
                                empleado.getId(), fechaInicio, fechaFin);

                        Map<Integer, List<DiasHoras>> diasPorSemana = diasEmpleado.stream()
                                .collect(Collectors.groupingBy(dh -> dh.getFecha().get(WeekFields.ISO.weekOfWeekBasedYear())));

                        double totalHorasExtras = diasPorSemana.values().stream()
                                .mapToDouble(this::calcularHorasExtrasPorSemana)
                                .sum();

                        long diasTrabajados = diasEmpleado.stream()
                                .filter(dh -> dh.getHorasTrabajadas() > 0)
                                .count();

                        long diasDescansoTrabajados = diasEmpleado.stream()
                                .filter(dh -> Boolean.TRUE.equals(dh.getEsDiaDescanso()))
                                .count();

                        long retardo = contarInconsistencias(diasEmpleado, 1L);
                        long falta = contarInconsistencias(diasEmpleado, 2L);
                        long permiso = contarInconsistencias(diasEmpleado, 3L);
                        long incapacidad = contarInconsistencias(diasEmpleado, 4L);

                        if (Boolean.TRUE.equals(periodo.isEstado())) {
                            boolean yaExiste = reporteGeneradoRepository.existsByPeriodoPagoAndCodigoEmpleado(periodo, empleado.getCodigoEmpleado());
                            if (!yaExiste) {
                                Reporte reporteEntity = new Reporte();
                                reporteEntity.setCodigoEmpleado(empleado.getCodigoEmpleado());
                                reporteEntity.setNombreEmpleado(empleado.getNombreCompleto());
                                reporteEntity.setDiasTrabajados((int) diasTrabajados);
                                reporteEntity.setFaltas((int) falta);
                                reporteEntity.setRetardos((int) retardo);
                                reporteEntity.setSalidasTempranas((int) permiso);
                                reporteEntity.setHorasExtras(BigDecimal.valueOf(totalHorasExtras));
                                reporteEntity.setDiasDescansoTrabajados((int) diasDescansoTrabajados);
                                reporteEntity.setPeriodoPago(periodo);

                                reporteGeneradoRepository.save(reporteEntity);
                            }
                        }

                        ReporteResponse reporte = new ReporteResponse(
                                empleado.getCodigoEmpleado(),
                                empleado.getNombreCompleto(),
                                (int) diasTrabajados,
                                falta,
                                retardo,
                                permiso,
                                totalHorasExtras,
                                diasDescansoTrabajados
                        );

                        log.debug("Reporte generado para empleado {}: {}", empleado.getCodigoEmpleado(), reporte);
                        reporteFinal.add(reporte);

                    } catch (Exception e) {
                        log.error("Error procesando datos del empleado ID={} ({}): {}",
                                empleado.getId(), empleado.getCodigoEmpleado(), e.getMessage(), e);
                    }
                }
            }

        } catch (DataAccessException dae) {
            log.error("Error al acceder a la base de datos: {}", dae.getMessage(), dae);
            throw new RuntimeException("Error al obtener datos de los empleados", dae);
        } catch (Exception e) {
            log.error("Error inesperado al generar el reporte: {}", e.getMessage(), e);
            throw new RuntimeException("Error interno al generar el reporte", e);
        }

        log.info("Reporte generado con {} registros", reporteFinal.size());
        return reporteFinal;
    }



    private double calcularHorasExtrasPorSemana(List<DiasHoras> semana) {
        try {
            List<DiasHoras> laborales = semana.stream()
                    .filter(dh -> !Boolean.TRUE.equals(dh.getEsDiaDescanso()))
                    .collect(Collectors.toList());

            double horasSemana = laborales.stream()
                    .mapToDouble(DiasHoras::getHorasTrabajadas)
                    .sum();

            return Math.max(0, horasSemana - 40);
        } catch (Exception e) {
            log.warn("Error al calcular horas extras de semana: {}", e.getMessage(), e);
            return 0;
        }
    }

    private long contarInconsistencias(List<DiasHoras> diasEmpleado, Long idInconsistencia) {
        return diasEmpleado.stream()
                .filter(dh -> dh.getMotivoInconsistencias() != null &&
                        Objects.equals(dh.getMotivoInconsistencias().getId(), idInconsistencia))
                .count();
    }

    public List<ReporteResponse> obtenerReportesGuardados(ReporteRequest request) {
        if (request.getTipoPeriodoId() == null) {
            throw new IllegalArgumentException("El tipo de periodo es obligatorio.");
        }

        List<Reporte> reportes;

        if (request.getPeriodoId() != null) {
            reportes = reporteGeneradoRepository.findByPeriodoPago_Id(request.getPeriodoId());
        } else {
            reportes = reporteGeneradoRepository.findByPeriodoPago_TipoPeriodoEmpleado_Id(request.getTipoPeriodoId());
        }

        return reportes.stream()
                .map(r -> new ReporteResponse(
                        r.getCodigoEmpleado(),
                        r.getNombreEmpleado(),
                        r.getDiasTrabajados(),
                        (long) r.getFaltas(),
                        (long) r.getRetardos(),
                        (long) r.getSalidasTempranas(),
                        r.getHorasExtras().doubleValue(),
                        (long) r.getDiasDescansoTrabajados()
                ))
                .collect(Collectors.toList());
    }

}