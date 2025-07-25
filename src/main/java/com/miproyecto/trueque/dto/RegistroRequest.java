package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroRequest {
    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    private Long motivoInconsistenciasId;
    private String comentario;
}
