package com.miproyecto.trueque.dto;

import com.miproyecto.trueque.model.catalogs.Inconsistencias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HorasDiasResponse {
    private Long id;

    private Long codigoEmpleado;
    private Long departamentoId;

    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    private Boolean diaDescanso;

    private Long motivoInconsistenciaId;
    private String motivoInconsistenciaDescripcion;
    private String comentario;


}
