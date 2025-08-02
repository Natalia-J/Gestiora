package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoEnRegistroResponse {
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
