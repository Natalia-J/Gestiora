package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.PeriodoEmpleadoResponseBin;
import com.miproyecto.trueque.dto.PeriodoPagoRequest;
import com.miproyecto.trueque.dto.PeriodoPagoResponse;
import com.miproyecto.trueque.interceptor.EmpresaContextHolder;
import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import com.miproyecto.trueque.model.catalogs.PeriodosCreadosEmpresa;
import com.miproyecto.trueque.model.catalogs.TipoPeriodoEmpleado;
import com.miproyecto.trueque.model.enums.TipoPeriodoEmpleadoEnum;
import com.miproyecto.trueque.repository.EmployeeRepository;
import com.miproyecto.trueque.repository.EmpresaRepository;
import com.miproyecto.trueque.repository.PeriodoPagoRepository;
import com.miproyecto.trueque.repository.catalog.PeriodosCreadosEmpleadoRepository;
import com.miproyecto.trueque.repository.catalog.PeriodosCreadosEmpresaRepository;
import com.miproyecto.trueque.repository.catalog.TipoPeriodoEmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodoPagoEmpleadoService {

    private final EmpresaRepository empresaRepository;
    private final PeriodosCreadosEmpresaRepository periodosEmpresaRepository;
    private final TipoPeriodoEmpleadoRepository tipoPeriodoEmpleadoRepository;
    private final PeriodosCreadosEmpleadoRepository periodosCreadosEmpleadoRepository;
    private final DiasHorasService diasHorasService;
    private final EmployeeRepository employeeRepository;
    private final PeriodoPagoRepository periodoPagoRepository;

    public List<PeriodoEmpleadoResponseBin> getPeriodosEmpleado(Long tipoPeriodo){
       List<PeriodoPago> periodos = periodoPagoRepository.findByTipoPeriodoEmpleado_Id(tipoPeriodo);

       return periodos.stream()
               .map(periodoPago-> new PeriodoEmpleadoResponseBin(periodoPago.getId(), periodoPago.getFechaInicio() + "-" + periodoPago.getFechaFin())).toList();

    }

    public List<PeriodoPagoResponse> generarPeriodosPago(List<PeriodoPagoRequest> solicitudes) {
        Long empresaId = EmpresaContextHolder.getEmpresaId();
        Company empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        PeriodosCreadosEmpresa periodoEmpresa = periodosEmpresaRepository
                .findByEmpresa_IdAndEstadoTrue(empresaId)
                .orElseThrow(() -> new RuntimeException("No hay un periodo activo para la empresa"));

        LocalDate fechaFinEmpresa = periodoEmpresa.getFechaTerminacion();

        List<PeriodoPago> periodosCreados = new ArrayList<>();

        for (PeriodoPagoRequest request : solicitudes) {
            TipoPeriodoEmpleado tipo = tipoPeriodoEmpleadoRepository.findById(request.getTipoPeriodoId())
                    .orElseThrow(() -> new RuntimeException("Tipo de periodo no encontrado"));

            LocalDate fechaInicio = request.getFechaInicio();

            if (fechaInicio.isBefore(periodoEmpresa.getFechaInicio()) || fechaInicio.isAfter(fechaFinEmpresa)) {
                throw new RuntimeException("La fecha de inicio para el tipo " + tipo.getTipoPeriodoEmpleado() +
                        " está fuera del rango del periodo activo de la empresa");
            }

            while (!fechaInicio.isAfter(fechaFinEmpresa)) {
                LocalDate fechaFin = calcularFechaFin(fechaInicio, tipo.getTipoPeriodoEmpleado());
                if (fechaFin.isAfter(fechaFinEmpresa)) {
                    fechaFin = fechaFinEmpresa;
                }

                PeriodoPago nuevo = new PeriodoPago();
                nuevo.setEmpresa(empresa);
                nuevo.setTipoPeriodoEmpleado(tipo);
                nuevo.setFechaInicio(fechaInicio);
                nuevo.setFechaFin(fechaFin);
                nuevo.setEstado(false);

                periodosCreados.add(nuevo);
                fechaInicio = fechaFin.plusDays(1);
            }
        }

        actualizarEstadoPeriodos(periodosCreados);

        List<PeriodoPago> guardados = periodosCreadosEmpleadoRepository.saveAll(periodosCreados);
        for (PeriodoPago periodo : guardados) {
            if (periodo.isEstado()) {
                List<Employee> empleados = employeeRepository.findByEmpresa_Id(empresaId);
                for (Employee empleado : empleados) {
                    diasHorasService.generarDiasParaPeriodoEmpleado(empleado.getId(), periodo.getId());
                }
            }
        }

        return guardados.stream().map(this::toResponse).toList();
    }

    private LocalDate calcularFechaFin(LocalDate inicio, TipoPeriodoEmpleadoEnum tipo) {
        return switch (tipo) {
            case SEMANAL -> inicio.plusDays(6);
            case QUINCENAL -> inicio.plusDays(14);
            case MENSUAL -> inicio.plusMonths(1).minusDays(1);
        };
    }

    private void actualizarEstadoPeriodos(List<PeriodoPago> periodos) {
        LocalDate hoy = LocalDate.now();
        for (PeriodoPago periodo : periodos) {
            boolean esActivo = !hoy.isBefore(periodo.getFechaInicio()) && !hoy.isAfter(periodo.getFechaFin());
            periodo.setEstado(esActivo);
        }
    }

    private PeriodoPagoResponse toResponse(PeriodoPago periodo) {
        PeriodoPagoResponse dto = new PeriodoPagoResponse();
        dto.setPeriodoId(periodo.getId());
        dto.setFechaInicio(periodo.getFechaInicio());
        dto.setFechaFin(periodo.getFechaFin());
        dto.setTipoPeriodo(periodo.getTipoPeriodoEmpleado().getTipoPeriodoEmpleado().name());
        dto.setEstado(periodo.isEstado());
        dto.setEmpresa(periodo.getEmpresa().getNombre());
        return dto;
    }
}

