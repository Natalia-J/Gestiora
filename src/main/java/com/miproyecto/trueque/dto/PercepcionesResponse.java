package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PercepcionesResponse {
    private String codigoEmpleado;
    private String nombreEmpleado;
    private BigDecimal horasExtras;
    private BigDecimal bono;
    private BigDecimal comisiones;
    private BigDecimal gratificaciones;
    private BigDecimal aguinaldoProporcional;
    private BigDecimal primaVacacional;
}
