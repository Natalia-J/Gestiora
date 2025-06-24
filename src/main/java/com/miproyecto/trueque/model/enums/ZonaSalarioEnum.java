package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ZonaSalarioEnum {
    ZLFN("Zona Libre de la Frontera Norte"),
    SMG("Salario Minimo General");

    private final String zona;
}
