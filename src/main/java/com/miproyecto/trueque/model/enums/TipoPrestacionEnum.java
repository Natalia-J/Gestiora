package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoPrestacionEnum {
    CONFIANZA("Confianza"),
    SINDICALIZADO("Sindicalizado");

    private final String description;
}
