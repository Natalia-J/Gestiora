package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.PeriodoEmpleadoResponseBin;
import com.miproyecto.trueque.dto.PeriodoEmpresaResponse;
import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import com.miproyecto.trueque.model.catalogs.PeriodosCreadosEmpresa;
import com.miproyecto.trueque.model.catalogs.TipoPeriodo;
import com.miproyecto.trueque.model.enums.TipoPeriodoEnum;
import com.miproyecto.trueque.repository.EmpresaRepository;
import com.miproyecto.trueque.repository.catalog.PeriodosCreadosEmpleadoRepository;
import com.miproyecto.trueque.repository.catalog.PeriodosCreadosEmpresaRepository;
import com.miproyecto.trueque.repository.catalog.TipoPeriodoRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class PeriodoEmpresaService {

    private final PeriodosCreadosEmpresaRepository periodosRepository;
    private final TipoPeriodoRepository tipoPeriodoRepository;
    private final EmpresaRepository empresaRepository;
    private final PeriodosCreadosEmpleadoRepository periodosCreadosEmpleadoRepository;


    public List<PeriodoEmpleadoResponseBin> getPeriodoEmpresas(Long empresaId) {
        List<PeriodoPago> periodoPagos = periodosCreadosEmpleadoRepository.findAllByEmpresa_IdAndEstadoTrue(empresaId);
        List<PeriodoEmpleadoResponseBin> periodoEmpresas = new ArrayList<>();

        for (PeriodoPago periodoPago : periodoPagos) {
            PeriodoEmpleadoResponseBin periodoEmpleadoResponseBin = new PeriodoEmpleadoResponseBin();
            periodoEmpleadoResponseBin.setId(periodoPago.getId());
            periodoEmpleadoResponseBin.setRangoFechas(periodoPago.getFechaInicio() + " - " + periodoPago.getFechaFin());

            periodoEmpresas.add(periodoEmpleadoResponseBin);
        }

        return periodoEmpresas;
    }


    public PeriodosCreadosEmpresa crearOActualizarPeriodo(Long empresaId, Long tipoPeriodoId, LocalDate fechaInicio) {
        Company empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));

        TipoPeriodo tipoPeriodo = tipoPeriodoRepository.findById(tipoPeriodoId)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de periodo no válido"));

        Optional<PeriodosCreadosEmpresa> periodoActivoOpt =
                periodosRepository.findByEmpresa_IdAndEstadoTrue(empresaId);

        if (periodoActivoOpt.isPresent()) {
            PeriodosCreadosEmpresa periodoActivo = periodoActivoOpt.get();

            if (periodoActivo.getFechaTerminacion().isBefore(LocalDate.now())) {
                periodoActivo.setEstado(false);
                periodosRepository.save(periodoActivo);

                return crearPeriodo(empresa, tipoPeriodo, periodoActivo.getFechaTerminacion().plusDays(1));
            } else {
                return periodoActivo;
            }
        }

        Optional<PeriodosCreadosEmpresa> ultimoOpt =
                periodosRepository.findFirstByEmpresa_IdOrderByFechaInicioDesc(empresaId);

        LocalDate nuevaFechaInicio = ultimoOpt
                .map(p -> p.getFechaTerminacion().plusDays(1))
                .orElse(fechaInicio);

        return crearPeriodo(empresa, tipoPeriodo, nuevaFechaInicio);
    }

    private PeriodosCreadosEmpresa crearPeriodo(Company empresa, TipoPeriodo tipoPeriodo, LocalDate fechaInicio) {
        LocalDate fechaFin = calcularFechaFin(fechaInicio, tipoPeriodo.getPeriodo());

        PeriodosCreadosEmpresa nuevoPeriodo = new PeriodosCreadosEmpresa();
        nuevoPeriodo.setEmpresa(empresa);
        nuevoPeriodo.setTipoPeriodo(tipoPeriodo);
        nuevoPeriodo.setFechaInicio(fechaInicio);
        nuevoPeriodo.setFechaTerminacion(fechaFin);
        nuevoPeriodo.setEstado(true);

        return periodosRepository.save(nuevoPeriodo);
    }

    private LocalDate calcularFechaFin(LocalDate inicio, TipoPeriodoEnum tipo) {
        return switch (tipo) {
            case MENSUAL -> inicio.plusMonths(1).minusDays(1);
            case TRIMESTRAL -> inicio.plusMonths(3).minusDays(1);
            case SEMESTRAL -> inicio.plusMonths(6).minusDays(1);
            case ANUAL -> inicio.plusYears(1).minusDays(1);
        };
    }

    public PeriodoEmpresaResponse mapToResponse(PeriodosCreadosEmpresa periodo) {
        PeriodoEmpresaResponse response = new PeriodoEmpresaResponse();
        response.setPeriodoId(periodo.getId());
        response.setFechaInicio(periodo.getFechaInicio());
        response.setFechaFin(periodo.getFechaTerminacion());
        response.setEstado(periodo.getEstado());
        response.setTipoPeriodo(periodo.getTipoPeriodo().getPeriodo().name());
        response.setEmpresa(periodo.getEmpresa().getNombre());
        return response;
    }
}

