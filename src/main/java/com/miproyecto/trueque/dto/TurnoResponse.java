package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoResponse {
    private Long turnoId;
    private String nombreTurno;
    private String horaEntrada;
    private String horaSalida;
    private double horasTurno;
    private String tipoJornada;
    private Set<String> diasDescanso;
}
