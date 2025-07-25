package com.miproyecto.trueque.service;

import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.catalogs.TipoPeriodo;
import com.miproyecto.trueque.model.enums.TipoPeriodoEnum;
import com.miproyecto.trueque.repository.EmpresaRepository;
import com.miproyecto.trueque.repository.catalog.TipoPeriodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoPeriodoService {
    private final TipoPeriodoRepository tipoPeriodoRepository;
    private final EmpresaRepository empresaRepository;

    public TipoPeriodo crearPeriodoInicial(Company empresa, TipoPeriodoEnum tipo, LocalDate fechaInicio) {
        LocalDate fechaFin = calcularFechaFin(fechaInicio, tipo);

        TipoPeriodo periodo = new TipoPeriodo();
        periodo.setEmpresa(empresa);
        periodo.setPeriodo(tipo);
        periodo.setFechaInicioPeriodo(fechaInicio);
        periodo.setFechaFinPeriodo(fechaFin);
        periodo.setCerrado(false);

        TipoPeriodo periodoGuardado = tipoPeriodoRepository.save(periodo);

        empresa.setPeriodoActivo(periodoGuardado);
        empresaRepository.save(empresa);

        return tipoPeriodoRepository.save(periodo);
    }

    public TipoPeriodo crearPeriodoInicialPorIdEmpresa(Long empresaId, TipoPeriodoEnum tipo, LocalDate fechaInicio) {
        Company empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        return crearPeriodoInicial(empresa, tipo, fechaInicio);
    }

    public LocalDate calcularFechaFin(LocalDate fechaInicio, TipoPeriodoEnum tipo) {
        return switch (tipo) {
            case QUINCENAL -> fechaInicio.plusDays(14);
            case MENSUAL -> fechaInicio.plusMonths(1).minusDays(1);
            case TRIMESTRAL -> fechaInicio.plusMonths(3).minusDays(1);
            case SEMESTRAL -> fechaInicio.plusMonths(6).minusDays(1);
            case ANUAL -> fechaInicio.plusYears(1).minusDays(1);
        };
    }

    public void cerrarPeriodoSiYaTermino(TipoPeriodo periodo) {
        if (LocalDate.now().isAfter(periodo.getFechaFinPeriodo()) && !periodo.isCerrado()) {
            periodo.setCerrado(true);
            tipoPeriodoRepository.save(periodo);
        }
    }

    public Optional<TipoPeriodo> obtenerPeriodoActivo(Company empresa) {
        return tipoPeriodoRepository.findTopByEmpresaAndCerradoFalse(empresa);
    }

    public List<TipoPeriodo> obtenerHistorial(Company empresa) {
        return tipoPeriodoRepository.findByEmpresaOrderByFechaInicioPeriodoDesc(empresa);
    }
}
