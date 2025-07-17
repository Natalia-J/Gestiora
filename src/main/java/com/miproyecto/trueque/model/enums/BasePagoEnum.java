package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BasePagoEnum {
    SUELDO("Sueldio"),
    COMISION("Comision"),
    DESTAJO("Destajo"),
    SUELDO_COMISION("Sueldo Comision"),
    SUELDO_DESTAJO("Sueldo Destajo");

    private final String desciption;

}
