package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HorasDiasRequest {
    private String horaEntrada;
    private String horaSaida;
    private Long inconsistencia;
    private String comentario;
    private Long justificacion;

}