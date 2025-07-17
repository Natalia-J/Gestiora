package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GeneroEnum {
    FEMENINO("Femenino"),
    Maculino("Masculino");

    private final String descripcion;
}
