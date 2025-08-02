package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroTablaRequest {
    private Long fechaId;
    private Long empleadoId;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
}
