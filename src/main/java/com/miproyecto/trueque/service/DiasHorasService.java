/*package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.HorasDiasRequest;
import com.miproyecto.trueque.model.Departamento;
import com.miproyecto.trueque.model.DiasHoras;
import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.model.catalogs.Inconsistencias;
import com.miproyecto.trueque.model.catalogs.TipoPeriodo;
import com.miproyecto.trueque.repository.*;
import com.miproyecto.trueque.repository.catalog.InconsistenciasRepository;
import com.miproyecto.trueque.repository.catalog.TipoPeriodoRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Service
public class DiasHorasService {

    private final DiasHorasRepository diasHorasRepository;
    private final DepartamentoRepository departamentoRepository;
    private final EmployeeRepository employeeRepository;
    private final InconsistenciasRepository inconsistenciasRepository;
    private final TipoPeriodoRepository tipoPeriodoRepository;

    public DiasHorasService(DiasHorasRepository diasHorasRepository,
                            DepartamentoRepository departamentoRepository,
                            EmployeeRepository employeeRepository,
                            InconsistenciasRepository inconsistenciasRepository,
                            TipoPeriodoRepository tipoPeriodoRepository) {
        this.diasHorasRepository = diasHorasRepository;
        this.departamentoRepository = departamentoRepository;
        this.employeeRepository = employeeRepository;
        this.inconsistenciasRepository = inconsistenciasRepository;
        this.tipoPeriodoRepository = tipoPeriodoRepository;
    }

    @Transactional
    public DiasHoras registrarDiaHora(HorasDiasRequest request) {
        Departamento departamento = departamentoRepository.findById(request.getDepartamento())
                .orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado"));
        Employee empleado = employeeRepository.findById(request.getEmpleado())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        Inconsistencias inconsistencia = null;
        if (request.getMotivoInconsistenciasId() != null) {
            inconsistencia = inconsistenciasRepository.findById(request.getMotivoInconsistenciasId())
                    .orElseThrow(() -> new IllegalArgumentException("Inconsistencia no encontrada"));
        }

        Inconsistencias motivo = null;
        if (request.getMotivoInconsistenciasId() != null) {
            motivo = inconsistenciasRepository.findById(request.getMotivoInconsistenciasId())
                    .orElseThrow(() -> new IllegalArgumentException("Motivo de inconsistencia no encontrado"));
        }

        TipoPeriodo tipoPeriodo = determinarTipoPeriodoPorFecha(request.getFecha());

        if (tipoPeriodo.isCerrado()) {
            throw new IllegalStateException("No se pueden registrar datos en un periodo cerrado.");
        }

        DiasHoras registro = new DiasHoras();
        registro.setDepartamento(departamento);
        registro.setEmpleado(empleado);
        registro.setFecha(request.getFecha());
        registro.setHoraEntrada(request.getHoraEntrada());
        registro.setHoraSalida(request.getHoraSalida());
        registro.setMotivoInconsistencias(inconsistencia);
        registro.setMotivoInconsistencias(motivo);
       // registro.setTipoPeriodo(tipoPeriodo);


        return diasHorasRepository.save(registro);
    }

    public List<DiasHoras> obtenerRegistrosPorPeriodo(Long tipoPeriodoId) {
        return diasHorasRepository.findByTipoPeriodoId(tipoPeriodoId);
    }

    public boolean verificarSiExisteRegistro(Long empleadoId, LocalDate fecha) {
        return diasHorasRepository.existsByFechaAndEmpleadoId(fecha, empleadoId);
    }

    private TipoPeriodo determinarTipoPeriodoPorFecha(LocalDate fecha) {
        List<TipoPeriodo> periodos = tipoPeriodoRepository.findAll();

        for (TipoPeriodo periodo : periodos) {
            if (!periodo.isCerrado() &&
                    ( !fecha.isBefore(periodo.getFechaInicioPeriodo()) && !fecha.isAfter(periodo.getFechaFinPeriodo()) )) {
                return periodo;
            }
        }

        throw new IllegalStateException("No se encontró un periodo activo para la fecha proporcionada");
    }
}*/












