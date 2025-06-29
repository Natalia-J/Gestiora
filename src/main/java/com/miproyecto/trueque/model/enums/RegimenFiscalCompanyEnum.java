package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RegimenFiscalCompanyEnum {
    GENERAL_PERSONAS_MORALES("REGIMEN GENERAL DE LEY PERSONAS MORALE"),
    SIMPLIFICADO_PERSONAS_MORALES("RÉGIMEN SIMPLIFICADO DE LEY PERSONAS MORALES"),
    FINES_NO_LUCRATIVOS("PERSONAS MORALES CON FINES NO LUCRATIVOS"),
    CONTRIBUYENTES("RÉGIMEN DE PEQUEÑOS CONTRIBUYENTES"),
    SUELDO_SALARIOS("RÉGIMEN DE SUELDOS Y SALARIOS E INGRESOS ASIMILADOS A SALARIOS"),
    ARRENDAMIENTO("RÉGIMEN DE ARRENDAMIENTO"),
    ENAJENACION("RÉGIMEN DE ENAJENACIÓN O ADQUISICIÓN DE BIENES"),
    DEMAS_INGRESOS("RÉGIMEN DE LOS DEMÁS INGRESOS"),
    CONSOLIDACION("RÉGIMEN DE CONSOLIDACIÓN"),
    RESIDENTES_EXTRANGERO("RÉGIMEN RESIDENTES EN EL EXTRANJERO SIN ESTABLECIMIENTO PERMANENTE EN MÉXICO"),
    INGRESOS_DIVIDENDOS("RÉGIMEN DE INGRESOS POR DIVIDENDOS (SOCIOS Y ACCIONISTAS)"),
    PERSONAS_FISICAS_ACTIVIDADES_EMPRESARIALES("RÉGIMEN DE LAS PERSONAS FÍSICAS CON ACTIVIDADES EMPRESARIALES Y PROFESIONALES"),
    INTERMEDIARIO_PERSONAS_FISICAS("RÉGIMEN INTERMEDIO DE LAS PERSONAS FÍSICAS CON ACTIVIDADES EMPRESARIALES");

    private final String regimenEmpresa;
}
