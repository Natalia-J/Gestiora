package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JustificacionEnum {
    SI("Con justificación"),
    NO("Sin justificación"),
    NO_APLICA("No aplica");

    private final String descripcion;
}
