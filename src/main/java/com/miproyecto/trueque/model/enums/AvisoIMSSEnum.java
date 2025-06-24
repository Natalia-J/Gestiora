package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AvisoIMSSEnum {
    ALTA("Alta"),
    BAJA("Baja"),
    CAMBIOS_SALARIALES("Cambios Salariales");

    private final String avisoImssEmpleado;
}
