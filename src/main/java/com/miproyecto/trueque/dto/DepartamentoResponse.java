package com.miproyecto.trueque.dto;

import com.miproyecto.trueque.model.Departamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class DepartamentoResponse {
    private Long id;
    private String codigo;
    private String nombreDepartamento;
    private List<EmpleadoDepartamentoResponse> empleados;

    public DepartamentoResponse(Departamento departamento) {
        this.id = departamento.getId();
        this.codigo = departamento.getCodigo();
        this.nombreDepartamento = departamento.getNombreDepartamento();
        this.empleados = departamento.getEmpleados().stream()
                .map(EmpleadoDepartamentoResponse::new)
                .collect(Collectors.toList());
    }


}
