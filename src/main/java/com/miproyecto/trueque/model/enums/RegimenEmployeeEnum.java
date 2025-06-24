package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RegimenEmployeeEnum {
    SUELDO_SALARIOS("Régimen de Sueldos y Salarios"),
    EMPRESARIALES_PROFECIONALES("Régimen de Actividades Empresariales y Profesionales"),
    INCORPORACION("Régimen de Incorporación Fiscal (RIF)"),
    PERSONAS_MORALES("Régimen de Personas Morales"),
    ARRENDAMIENTO("Régimen de Arrendamiento"),
    INTERMEDIOS("Régimen de Intermedios"),
    ENAJENACION("Régimen de Enajenación de Bienes"),
    CONSOLIDACION("Régimen de Consolidación Fiscal");

    private final String regimenEmpleado;
}
