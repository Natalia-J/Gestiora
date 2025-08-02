package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteResponse {
    private String codigoEmpleado;
    private String nombreEmpleado;
    private int diasTrabajados;
    private Long faltas;
    private Long retardos;
    private Long salidatemprana;
    private double horasExtras;
    private Long diasDescansoTrabajados;

}
