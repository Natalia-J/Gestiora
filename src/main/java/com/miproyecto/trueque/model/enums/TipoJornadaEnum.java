package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoJornadaEnum {
    DIURNA("Diurna"),
    NOCTURNA("Nocturna"),
    MIXTA("Mixta");

    private final String descripcion;
}
