package com.miproyecto.trueque.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class HorasDiasResponse {
    private Long id;
    private Long codigoEmpleado;
    private String nombreEmpleado;

    private Long departamentoId;
    private String nonbreDepartamento;

    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    private BigDecimal horasReales;
    private BigDecimal horasDobles;
    private BigDecimal horasTriples;

    private boolean feriado;
    private boolean descanso;

    private String inconsistencias;
    private String motivoInconsistencias;
}
