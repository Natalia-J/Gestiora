package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.PeriodoPagoRequest;
import com.miproyecto.trueque.dto.PeriodoPagoResponse;
import com.miproyecto.trueque.interceptor.EmpresaContextHolder;
import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import com.miproyecto.trueque.model.catalogs.TipoPeriodoEmpleado;
import com.miproyecto.trueque.repository.EmpresaRepository;
import com.miproyecto.trueque.repository.catalog.PeriodoPagoRepository;
import com.miproyecto.trueque.repository.catalog.TipoPeriodoEmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodoEmpleadoService {

    private final PeriodoPagoRepository periodoPagoRepository;
    private final TipoPeriodoEmpleadoRepository tipoPeriodoEmpleadoRepository;
    private final EmpresaRepository empresaRepository;

    public PeriodoPagoResponse crearPeriodoPago(PeriodoPagoRequest request) {
        Long empresaId = EmpresaContextHolder.getEmpresaId();
        Company empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada en contexto"));

        TipoPeriodoEmpleado tipoPeriodo = tipoPeriodoEmpleadoRepository.findById(request.getTipoPeriodoEmpleadoId())
                .orElseThrow(() -> new RuntimeException("TipoPeriodoEmpleado no encontrado"));

        LocalDate fechaInicio = request.getFechaInicio();
        LocalDate fechaFin;

        switch (tipoPeriodo.getTipoPeriodoEmpleado()) {
            case SEMANAL:
                fechaFin = fechaInicio.plusDays(6);
                break;
            case QUINCENAL:
                fechaFin = fechaInicio.plusDays(14);
                break;
            case MENSUAL:
                fechaFin = fechaInicio.plusMonths(1).minusDays(1);
                break;
            default:
                throw new RuntimeException("Tipo de periodo no soportado");
        }

        PeriodoPago periodoPago = new PeriodoPago();
        periodoPago.setEmpresa(empresa);
        periodoPago.setTipoPeriodoEmpleado(tipoPeriodo);
        periodoPago.setFechaInicio(fechaInicio);
        periodoPago.setFechaFin(fechaFin);
        periodoPago.setCerrado(false);

        periodoPago = periodoPagoRepository.save(periodoPago);

        return mapToResponse(periodoPago);
    }

    public List<PeriodoPagoResponse> generarPeriodosHastaFinDeAnio(PeriodoPagoRequest request) {
        TipoPeriodoEmpleado tipoPeriodo = tipoPeriodoEmpleadoRepository.findById(request.getTipoPeriodoEmpleadoId())
                .orElseThrow(() -> new RuntimeException("TipoPeriodoEmpleado no encontrado"));

        LocalDate inicio = request.getFechaInicio();
        LocalDate finAnio = LocalDate.of(inicio.getYear(), 12, 31);

        List<PeriodoPagoResponse> periodos = new ArrayList<>();

        while (inicio.isBefore(finAnio)) {
            LocalDate fechaFin;
            switch (tipoPeriodo.getTipoPeriodoEmpleado()) {
                case SEMANAL:
                    fechaFin = inicio.plusDays(6);
                    break;
                case QUINCENAL:
                    fechaFin = inicio.plusDays(14);
                    break;
                case MENSUAL:
                    fechaFin = inicio.plusMonths(1).minusDays(1);
                    break;
                default:
                    throw new RuntimeException("Tipo de periodo no soportado");
            }

            PeriodoPagoResponse response = new PeriodoPagoResponse();
            response.setFechaInicio(inicio);
            response.setFechaFin(fechaFin);
            response.setCerrado(false);
            response.setTipoPeriodo(tipoPeriodo.getTipoPeriodoEmpleado().name());

            periodos.add(response);

            inicio = fechaFin.plusDays(1);
        }

        return periodos;
    }

    public void cerrarPeriodo(Long id) {
        PeriodoPago periodo = periodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Periodo no encontrado"));

        if (periodo.isCerrado()) {
            throw new IllegalStateException("Este periodo ya está cerrado");
        }

        periodo.setCerrado(true);
        periodoPagoRepository.save(periodo);
    }

    public PeriodoPagoResponse getUltimoPeriodoAbiertoPorEmpresa(Long empleadoId, Long empresaId) {
        Company empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        PeriodoPago periodo = periodoPagoRepository
                .findTopByEmpresaAndCerradoFalseOrderByFechaInicioDesc(empresa)
                .orElseThrow(() -> new RuntimeException("No hay periodos abiertos para esta empresa"));

        return mapToResponse(periodo);
    }




    private PeriodoPagoResponse mapToResponse(PeriodoPago p) {
        PeriodoPagoResponse response = new PeriodoPagoResponse();
        response.setId(p.getId());
        response.setFechaInicio(p.getFechaInicio());
        response.setFechaFin(p.getFechaFin());
        response.setCerrado(p.isCerrado());
        response.setTipoPeriodo(p.getTipoPeriodoEmpleado().getTipoPeriodoEmpleado().name());
        return response;
    }

    public PeriodoPagoResponse obtenerPeriodoActual() {
        Long empresaId = EmpresaContextHolder.getEmpresaId();
        LocalDate hoy = LocalDate.now();

        PeriodoPago periodo = periodoPagoRepository
                .findByEmpresaAndFecha(empresaId, hoy)
                .orElseThrow(() -> new RuntimeException("No hay un periodo actual para hoy."));

        return mapToResponse(periodo);
    }


}
