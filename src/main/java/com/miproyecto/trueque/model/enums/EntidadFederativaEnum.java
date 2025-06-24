package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntidadFederativaEnum {
    AGUASCALIENTES("Aguascalientes"),
    BAJA_CALIFORNIA("Baja California"),
    BAJA_CALIFORNIA_SUR("Baja California Sur"),
    CAMPECHE("Campeche"),
    COAHUILA_ZARAGOZA("Coahuila de Zaragoza"),
    COLIMA("Colima"),
    CHIAPAS("Chiapas"),
    CHIHUAHUA("Chihuahua"),
    CIUDAD_MEXICO("Ciudad de México"),
    DURANGO("Durango"),
    GUANAJUATO("Guanajuato"),
    GUERRERO("Guerrero"),
    HIDALGO("Hidalgo"),
    JALISCO("Jalisco"),
    MEXICO("México"),
    MICHOACAN_OCAMPO("Michoacán de Ocampo"),
    MORELOS("Morelos"),
    NAYARIT("Nayarit"),
    NUEVO_LEON("Nuevo León"),
    OAXACA("Oaxaca"),
    PUEBLA("Puebla"),
    QUERETARO("Querétaro"),
    QUINTANA_ROO("Quintana Roo"),
    SAN_LUIS_POTOSI("San Luis Potosí"),
    SINALOA("Sinaloa"),
    SONORA("Sonora"),
    TABASCO("Tabasco"),
    TAMAULIPAS("Tamaulipas"),
    TLAXCALA("Tlaxcala"),
    VERACRUZ("Veracruz de Ignacio de la Llave"),
    YUCATAN("Yucatán"),
    ZACATECAS("Zacatecas");

    private final String entidadFederativa;
}
