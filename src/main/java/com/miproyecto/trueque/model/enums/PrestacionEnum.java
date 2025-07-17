package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PrestacionEnum {
    CONFIANZA("Confianza"),
    SINDICALIZADO("Sindicalizado");

    private final String decription;
}
