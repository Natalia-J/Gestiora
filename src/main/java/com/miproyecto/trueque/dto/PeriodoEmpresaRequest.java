package com.miproyecto.trueque.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
public class PeriodoEmpresaRequest {
    private Long periodoEmpresaId;
    private LocalDate fechaInicio;
}
