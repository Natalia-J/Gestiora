package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EstadoCivilEnum {
    SOLTERO("Soltero"),
    CASADO("Casado"),
    VIUDO("Viudo"),
    UNION_LIBRE("Union Libre"),
    DIVORCIADO("Divorciado");

    private final String descripcion;
}
