package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MetodoPagoEnum {
    TRANFERENCIA("trasnferencia electronica"),
    TARJETA("tarjeta de credito"),
    MONEDERO("monedereo electronico"),
    DINERO_ELECTRONICO("dinero electronico"),
    VALES("vales de despensa"),
    DACION_PAGO("dacion de pago"),
    EFECTIVO("efectivo"),
    CHEQUE("cheque nominativo");

    private final String metodoPago;

}
