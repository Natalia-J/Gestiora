package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CodigEmpleEnum {
    ALFANUMERICO("Alfanumérico"),
    NUMERICO("Numérico");

    private final String descripcion;
}
