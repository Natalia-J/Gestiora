package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoContratoEnum {
    TIEMPO_DETERMINADO("Contrato por Tiempo Determinado"),
    TIEMPO_INDETERMINADO("Contrato por Tiempo Indeterminado"),
    OBRA_SERVICIO("Contrato por Obra o Servicio"),
    TEMPORADA("Contrato por Temporada"),
    CAPACITACION_INICIAL("Contrato para Capacitación Inicial"),
    FORMACION_APRENDIZAJE("Contrato de formacion y aprendizajes"),
    PERIODO_PRUEBA("Contrato por Periodo de Prueba");

    private final String descripcion;

}
