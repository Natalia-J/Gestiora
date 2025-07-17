package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoPeriodoEnum {
    QUINCENAL("Quincenal"),
    MENSUAL("Mensual"),
    SEMESTRAL("Semestral"),
    TRIMESTRAL("Trimestral"),
    ANUAL("Anual");

    private final String description;
}
