package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseCotizacionEnum {
    FIJO("Fijo"),
    VARIABLE("Variable"),
    MIXTO("Mixto");

    private final String description;
}
