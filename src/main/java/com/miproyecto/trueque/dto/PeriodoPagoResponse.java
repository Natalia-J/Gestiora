package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PeriodoPagoResponse {
    private Long periodoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipoPeriodo;
    private Boolean estado;
    private String empresa;
}
