package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrenominaResponse {
    private BigDecimal sueldoBase;
    private BigDecimal horasExtras;
    private BigDecimal bono;
    private BigDecimal comisiones;
    private BigDecimal gratificaciones;
    private BigDecimal aguinaldoProporcional;
    private BigDecimal primaVacacional;
    private BigDecimal isr;
    private BigDecimal imss;
    private BigDecimal infonavit;
    private BigDecimal otrasDeducciones;
    private Double totalNeto;
}
