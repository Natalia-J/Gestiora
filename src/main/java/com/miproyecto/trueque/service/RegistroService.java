/*package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.HorasDiasResponse;
import com.miproyecto.trueque.model.DiasHoras;
import com.miproyecto.trueque.repository.DiasHorasRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Service
public class RegistroService {

    private EmployeeService employeeService;
    private DiasHorasRepository diasHorasRepository;

    public RegistroService(EmployeeService employeeService, DiasHorasRepository diasHorasRepository){;
        this.employeeService=employeeService;
        this.diasHorasRepository=diasHorasRepository;
    }

    public List<HorasDiasResponse> obtenerHorasPorFiltros(String codigoEmpleado, Long departamentoId, Long empleadoId) {
        List<DiasHoras> registros;

        if (codigoEmpleado != null && !codigoEmpleado.isEmpty()) {
            registros = diasHorasRepository.findByEmpleadoCodigo(codigoEmpleado);

        } else if (departamentoId != null && codigoEmpleado != null ) {
            registros = diasHorasRepository.findByEmpleadoDepartamentoIdAndEmpleadoId(departamentoId, empleadoId);

        } else {
            throw new IllegalArgumentException("proporcionar combinación válida");
        }

        return registros.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

}*/
