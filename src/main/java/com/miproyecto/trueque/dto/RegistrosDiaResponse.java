package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrosDiaResponse {
    private String comentario;
    private boolean esDiaDescanso;
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private String horasTrabajadas;
    private Long inconsistencia;
}
