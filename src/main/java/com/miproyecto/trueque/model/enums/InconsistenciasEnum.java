package com.miproyecto.trueque.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InconsistenciasEnum {
    FALTA("El empleado no asistió al trabajo."),
    RETARDO("El empleado llegó tarde."),
    SALIDA_TEMPRANA("El empleado se fue antes de terminar su jornada."),
    INASISTENCIA_PARCIAL("El empleado se ausentó parte del turno."),
    PERMISO_CON_GOCE("El empleado no asistió, pero tenía permiso (con goce de sueldo)."),
    PERMISO_SIN_GOCE("El empleado no asistió, pero tenía permiso (sin goce de sueldo)."),
    ERROR_DE_REGISTRO("No se capturó correctamente entrada o salida."),
    DIA_FESTIVO_TRABAJADO("El empleado trabajó en día feriado."),
    DIA_DE_DESCANSO_LABORADO("El empleado trabajó en su día de descanso.");

    private final String descripcion;
}
