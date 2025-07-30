package com.miproyecto.trueque.dto;

import com.miproyecto.trueque.model.catalogs.TipoPeriodoEmpleado;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PeriodoPagoRequest {
    private Long tipoPeriodoId;
    private LocalDate fechaInicio;
}
