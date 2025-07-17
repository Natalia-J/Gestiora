package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HorasDiasRequest {
    private Long departamento;
    private Long empleado;

    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    private Long inconsistencias;
    private Long motivoInconsistenciasId;

    private boolean feriado;
    private boolean descanso;

    private Long tipoPeriodo;

}