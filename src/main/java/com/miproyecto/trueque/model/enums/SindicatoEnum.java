package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SindicatoEnum {
    SI("Si"),
    NO("No");

    private final String description;
}
