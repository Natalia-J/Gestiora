package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RegimenFiscalCompanyEnum {
    GENERAL_PERSONAS_MORALES("Régimen General de Ley Personas Morales"),
    SIMPLIFICADO_PERSONAS_MORALES("Régimen Simplificado de Ley Personas Morales"),
    FINES_NO_LUCRATIVOS("Personas Morales con Fines No Lucrativos"),
    CONTRIBUYENTES("Régimen de Pequeños Contribuyentes"),
    SUELDO_SALARIOS("Régimen de Sueldos y Salarios e Ingresos Asimilados a Salarios"),
    ARRENDAMIENTO("Régimen de Arrendamiento"),
    ENAJENACION("Régimen de Enajenación o Adquisición de Bienes"),
    DEMAS_INGRESOS("Régimen de los Demás Ingresos"),
    CONSOLIDACION("Régimen de Consolidación"),
    RESIDENTES_EXTRANJERO("Régimen Residentes en el Extranjero sin Establecimiento Permanente en México"),
    INGRESOS_DIVIDENDOS("Régimen de Ingresos por Dividendos (Socios y Accionistas)"),
    PERSONAS_FISICAS_ACTIVIDADES_EMPRESARIALES("Régimen de las Personas Físicas con Actividades Empresariales y Profesionales"),
    INTERMEDIARIO_PERSONAS_FISICAS("Régimen Intermedio de las Personas Físicas con Actividades Empresariales");

    private final String descripcion;
}
