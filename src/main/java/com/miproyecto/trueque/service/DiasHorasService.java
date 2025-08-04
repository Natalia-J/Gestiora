package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.*;
import com.miproyecto.trueque.model.DiasHoras;
import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.model.Turno;
import com.miproyecto.trueque.model.catalogs.DiaSemana;
import com.miproyecto.trueque.model.catalogs.Inconsistencias;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import com.miproyecto.trueque.model.enums.DiaSemanaEnum;
import com.miproyecto.trueque.model.enums.InconsistenciasEnum;
import com.miproyecto.trueque.repository.DepartamentoRepository;
import com.miproyecto.trueque.repository.DiasHorasRepository;
import com.miproyecto.trueque.repository.EmployeeRepository;
import com.miproyecto.trueque.repository.catalog.InconsistenciasRepository;
import com.miproyecto.trueque.repository.catalog.PeriodosCreadosEmpleadoRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Setter
@Getter
@Service
public class DiasHorasService {
    private final DiasHorasRepository diasHorasRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartamentoRepository departamentoRepository;
    private final TurnoService turnoService;
    private final PeriodosCreadosEmpleadoRepository  periodosCreadosEmpleadoRepository;
    private final InconsistenciasService inconsistenciasService;
    private final InconsistenciasRepository inconsistenciasRepository;

    public DiasHorasService(DiasHorasRepository diasHorasRepository, EmployeeRepository employeeRepository, DepartamentoRepository departamentoRepository, TurnoService turnoService, PeriodosCreadosEmpleadoRepository periodosCreadosEmpleadoRepository,
                            InconsistenciasService inconsistenciasService, InconsistenciasRepository inconsistenciasRepository) {
        this.diasHorasRepository = diasHorasRepository;
        this.employeeRepository = employeeRepository;
        this.departamentoRepository = departamentoRepository;
        this.turnoService = turnoService;
        this.periodosCreadosEmpleadoRepository = periodosCreadosEmpleadoRepository;
        this.inconsistenciasService = inconsistenciasService;
        this.inconsistenciasRepository = inconsistenciasRepository;
    }

    public BusquedaDatosResponse buscarDatos(BusquedaDatosRequest request) {
        Employee empleado = obtenerEmpleadoDesdeRequest(request);
        PeriodoPago periodo = obtenerPeriodoUsandoRequest(request, empleado);

        if (periodo == null) {
            throw new RuntimeException("No hay periodo activo ni se proporcionó uno");
        }

        generarDiasParaUnPeriodo(empleado, periodo);

        List<DiasHoras> diasHorasList = diasHorasRepository.findByEmpleado_Id(empleado.getId()).stream()
                .filter(dh -> dh.getPeriodoActivo().getId().equals(periodo.getId()))
                .toList();

        List<RegistrosDiaResponse> registros = new ArrayList<>(diasHorasList.stream()
                .map(dh -> new RegistrosDiaResponse(
                        dh.getComentario(),
                        dh.getEsDiaDescanso(),
                        dh.getFecha(),
                        dh.getHoraEntrada() != null ? dh.getHoraEntrada() : null,
                        dh.getHoraSalida() != null ? dh.getHoraSalida() : null,
                        dh.getHorasReales() != null ? dh.getHorasReales().toPlainString() : null,
                        dh.getMotivoInconsistencias() != null ? dh.getMotivoInconsistencias().getId() : null
                ))
                .toList());

// Ordenar registros por fecha (ascendente, nulls al final)
        registros.sort(Comparator.comparing(RegistrosDiaResponse::getFecha, Comparator.nullsLast(Comparator.naturalOrder())));

        TurnoResponse turnoResponse = null;
        if (empleado.getTurno() != null) {
            turnoResponse = turnoService.mapearATurnoResponse(empleado.getTurno());
        }

        PeriodoEnRegistroResponse periodoResponse = new PeriodoEnRegistroResponse(
                periodo.getId(),
                periodo.getFechaInicio(),
                periodo.getFechaFin()
        );

        return new BusquedaDatosResponse(
                empleado.getCodigoEmpleado(),
                empleado.getDepartamentoEmple().getId(),
                empleado.getId(),
                turnoResponse,
                periodoResponse,
                registros // ← Aquí ya pasas la lista ordenada
        );


    }


    private Employee obtenerEmpleadoDesdeRequest(BusquedaDatosRequest request) {
        if (request.getCodigoEmpleado() != null && !request.getCodigoEmpleado().isEmpty()) {
            return employeeRepository.findByCodigoEmpleado(request.getCodigoEmpleado())
                    .orElseThrow(() -> new RuntimeException("No existe empleado con ese código"));
        } else if (request.getEmpleadoId() != null && request.getDepartamentoId() != null) {
            Employee empleado = employeeRepository.findById(request.getEmpleadoId())
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
            if (!empleado.getDepartamentoEmple().getId().equals(request.getDepartamentoId())) {
                throw new RuntimeException("Empleado no pertenece al departamento indicado");
            }
            return empleado;
        } else {
            throw new RuntimeException("Parámetros insuficientes para la búsqueda");
        }
    }

    private PeriodoPago obtenerPeriodoUsandoRequest(BusquedaDatosRequest request, Employee empleado) {
        if (request.getPeriodoId() != null) {
            return periodosCreadosEmpleadoRepository.findById(request.getPeriodoId())
                    .orElseThrow(() -> new RuntimeException("Periodo no encontrado con ID: " + request.getPeriodoId()));
        } else {
            return periodosCreadosEmpleadoRepository
                    .findByEmpresa_IdAndEstadoTrue(empleado.getEmpresa().getId())
                    .orElse(null);
        }
    }

    private DiaSemanaEnum mapDayOfWeekToEnum(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY -> DiaSemanaEnum.LUNES;
            case TUESDAY -> DiaSemanaEnum.MARTES;
            case WEDNESDAY -> DiaSemanaEnum.MIERCOLES;
            case THURSDAY -> DiaSemanaEnum.JUEVES;
            case FRIDAY -> DiaSemanaEnum.VIERNES;
            case SATURDAY -> DiaSemanaEnum.SABADO;
            case SUNDAY -> DiaSemanaEnum.DOMINGO;
        };
    }


    private void generarDiasParaUnPeriodo(Employee empleado, PeriodoPago periodo) {
        Set<DiaSemana> diasDescansoTurno = empleado.getTurno() != null ? empleado.getTurno().getDiasDescanso() : Set.of();

        List<DiasHoras> diasHorasGenerados = new ArrayList<>();
        LocalDate fecha = periodo.getFechaInicio();

        while (!fecha.isAfter(periodo.getFechaFin())) {
            boolean yaExiste = diasHorasRepository.existsByEmpleadoAndPeriodoActivoAndFecha(empleado, periodo, fecha);
            if (!yaExiste) {
                DayOfWeek dayOfWeek = fecha.getDayOfWeek();
                DiaSemanaEnum diaSemanaEnum = mapDayOfWeekToEnum(dayOfWeek);

                boolean esDescanso = diasDescansoTurno.stream()
                        .anyMatch(d -> d.getDiaSemana() == diaSemanaEnum);

                DiasHoras dia = new DiasHoras();
                dia.setEmpleado(empleado);
                dia.setDepartamento(empleado.getDepartamentoEmple());
                dia.setFecha(fecha);
                dia.setEsDiaDescanso(esDescanso);
                dia.setPeriodoActivo(periodo);
                diasHorasGenerados.add(dia);
            }
            fecha = fecha.plusDays(1);
        }

        if (!diasHorasGenerados.isEmpty()) {
            diasHorasRepository.saveAll(diasHorasGenerados);
        }
    }

    public void generarDiasParaPeriodoEmpleado(Long empleadoId, Long periodoId) {
        Employee empleado = employeeRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        PeriodoPago periodo;

        if (periodoId != null) {
            periodo = periodosCreadosEmpleadoRepository.findById(periodoId)
                    .orElseThrow(() -> new RuntimeException("Periodo no encontrado"));
            if (!periodo.getTipoPeriodoEmpleado().getId().equals(empleado.getTipoPeriodo().getId())) {
                throw new RuntimeException("El periodo no corresponde al tipo de periodo del empleado");
            }
        } else {
            periodo = periodosCreadosEmpleadoRepository
                    .findByEmpresa_IdAndEstadoTrueAndTipoPeriodoEmpleadoId(
                            empleado.getEmpresa().getId(),
                            empleado.getTipoPeriodo().getId())
                    .orElseThrow(() -> new RuntimeException("No hay periodo activo para el tipo de periodo del empleado"));
        }

        generarDiasParaUnPeriodo(empleado, periodo);
    }

    public BigDecimal calcularHorasTrabajadas(LocalTime entrada, LocalTime salida) {
        if (entrada == null || salida == null) return BigDecimal.ZERO;

        if (salida.isBefore(entrada)) {
            salida = salida.plusHours(24);
        }

        Duration duracion = Duration.between(entrada, salida);
        long minutosTotales = duracion.toMinutes();

        return BigDecimal.valueOf(minutosTotales).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
    }

    private boolean calcularEsDiaDescanso(Employee empleado, LocalDate fecha) {
        Set<DiaSemana> diasDescansoTurno = empleado.getTurno() != null ? empleado.getTurno().getDiasDescanso() : Set.of();

        DayOfWeek dayOfWeek = fecha.getDayOfWeek();
        DiaSemanaEnum diaSemanaEnum = mapDayOfWeekToEnum(dayOfWeek);

        return diasDescansoTurno.stream()
                .anyMatch(d -> d.getDiaSemana() == diaSemanaEnum);
    }

    private static final int MINUTOS_TOLERANCIA_RETARDO = 5;
    private static final int MINUTOS_TOLERANCIA_SALIDA = 5;


    @Transactional
    public void guardarRegistro(GuardarRegistroRequest request) {
        DiasHoras registro = diasHorasRepository
                .findByEmpleado_IdAndPeriodoActivo_IdAndFecha(
                        request.getEmpleadoId(),
                        request.getPeriodoId(),
                        request.getFecha()
                )
                .orElseThrow(() -> new RuntimeException("Registro de día no encontrado"));

        boolean esDiaDescanso = registro.getEsDiaDescanso();
        Long inconsistenciaId = request.getInconsistenciaId();

        if (esDiaDescanso) {
            if (inconsistenciaId == null) {
                throw new RuntimeException("No se puede registrar asistencia en un día de descanso sin indicar la inconsistencia correspondiente.");
            }

            Inconsistencias inconsistencia = inconsistenciasRepository
                    .findById(inconsistenciaId)
                    .orElseThrow(() -> new RuntimeException("Inconsistencia no encontrada"));

            if (inconsistencia.getInconsistencias() != InconsistenciasEnum.DIA_DE_DESCANSO_LABORADO) {
                throw new RuntimeException("En un día de descanso solo se permite la inconsistencia de tipo DIA_DE_DESCANSO_LABORADO.");
            }
        } else {
            if (inconsistenciaId != null) {
                Inconsistencias inconsistencia = inconsistenciasRepository
                        .findById(inconsistenciaId)
                        .orElseThrow(() -> new RuntimeException("Inconsistencia no encontrada"));

                if (inconsistencia.getInconsistencias() == InconsistenciasEnum.DIA_DE_DESCANSO_LABORADO) {
                    throw new RuntimeException("No se puede asignar la inconsistencia DIA_DE_DESCANSO_LABORADO en un día que no es de descanso.");
                }
            }
        }

        registro.setHoraEntrada(request.getHoraEntrada());
        registro.setHoraSalida(request.getHoraSalida());

        if (request.getHoraEntrada() != null && request.getHoraSalida() != null) {
            BigDecimal horasTrabajadas = calcularHorasTrabajadas(
                    request.getHoraEntrada(),
                    request.getHoraSalida()
            );
            registro.setHorasReales(horasTrabajadas);
        } else {
            registro.setHorasReales(BigDecimal.ZERO);
        }

        Turno turno = registro.getEmpleado().getTurno();
        boolean inconsistenciaDetectada = false;

        if (turno != null && !esDiaDescanso) {
            LocalTime horaEntradaTurno = LocalTime.parse(turno.getHoraEntrada());
            LocalTime horaSalidaTurno = LocalTime.parse(turno.getHoraSalida());

            if (request.getHoraEntrada() != null &&
                    request.getHoraEntrada().isAfter(horaEntradaTurno.plusMinutes(MINUTOS_TOLERANCIA_RETARDO))) {

                InconsistenciasEnum tipo = InconsistenciasEnum.RETARDO;
                inconsistenciasService.procesarInconsistencia(registro, tipo, null);

                Inconsistencias entidad = inconsistenciasRepository
                        .findByInconsistencias(tipo)
                        .orElseThrow(() -> new RuntimeException("Inconsistencia RETARDO no encontrada"));
                registro.setMotivoInconsistencias(entidad);
                inconsistenciaDetectada = true;
            }
            if (!esDiaDescanso && turno != null && request.getHoraEntrada() != null && request.getHoraSalida() != null) {
                BigDecimal horasTrabajadas = registro.getHorasReales();

                BigDecimal horasTurno = calcularHorasTrabajadas(
                        LocalTime.parse(turno.getHoraEntrada()),
                        LocalTime.parse(turno.getHoraSalida())
                );

                if (horasTrabajadas.compareTo(horasTurno) > 0) {
                    BigDecimal horasExtra = horasTrabajadas.subtract(horasTurno);
                    registro.setHorasExtras(horasExtra);
                } else {
                    registro.setHorasExtras(BigDecimal.ZERO);
                }
            } else {
                registro.setHorasExtras(BigDecimal.ZERO);
            }


            if (!inconsistenciaDetectada &&
                    request.getHoraSalida() != null &&
                    request.getHoraSalida().isBefore(horaSalidaTurno.minusMinutes(MINUTOS_TOLERANCIA_SALIDA))) {

                InconsistenciasEnum tipo = InconsistenciasEnum.SALIDA_TEMPRANA;
                inconsistenciasService.procesarInconsistencia(registro, tipo, null);

                Inconsistencias entidad = inconsistenciasRepository
                        .findByInconsistencias(tipo)
                        .orElseThrow(() -> new RuntimeException("Inconsistencia SALIDA_TEMPRANA no encontrada"));
                registro.setMotivoInconsistencias(entidad);
                inconsistenciaDetectada = true;
            }
        }

        if (!inconsistenciaDetectada && inconsistenciaId != null) {
            Inconsistencias inconsistenciaEntidad = inconsistenciasRepository
                    .findById(inconsistenciaId)
                    .orElseThrow(() -> new RuntimeException("Inconsistencia no encontrada"));

            InconsistenciasEnum inconsistencia = inconsistenciaEntidad.getInconsistencias();
            inconsistenciasService.procesarInconsistencia(registro, inconsistencia, null);
            registro.setMotivoInconsistencias(inconsistenciaEntidad);
        }

        if (!inconsistenciaDetectada && inconsistenciaId == null) {
            registro.setComentario(null);
            registro.setMotivoInconsistencias(null);
        }

        diasHorasRepository.save(registro);
    }

}