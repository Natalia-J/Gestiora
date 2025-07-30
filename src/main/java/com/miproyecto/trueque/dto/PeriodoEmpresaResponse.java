package com.miproyecto.trueque.dto;

import com.miproyecto.trueque.model.catalogs.TipoPeriodo;
import com.miproyecto.trueque.model.enums.TipoPeriodoEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
public class PeriodoEmpresaResponse {
    private Long periodoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipoPeriodo;
    private Boolean estado;
    private String empresa;
}
