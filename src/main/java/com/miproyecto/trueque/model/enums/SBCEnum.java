package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SBCEnum {
    FIJO("SBC porte fijo"),
    VARIABLE("SBC porte variable"),
    TIPADO("SBC(tipado a 25 UMAS");

    private final String descripcion;
}
