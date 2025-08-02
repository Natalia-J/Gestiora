package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PercepcionesRequest {
    private String codigoEmpleado;
    private BigDecimal bono;
    private BigDecimal comisiones;
    private BigDecimal gratificaciones;
    private BigDecimal aguinaldoProporcional;
    private BigDecimal primaVacacional;
}
