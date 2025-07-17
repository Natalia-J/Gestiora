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
public class TurnoRequest {
    private String nombreTurno;
    private String horaEntrada;
    private String horaSalida;
    private Long tipoJornada;
    private Set<Long> diasDescanso;
}
