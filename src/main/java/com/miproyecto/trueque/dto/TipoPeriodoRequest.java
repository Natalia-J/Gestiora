package com.miproyecto.trueque.dto;

import com.miproyecto.trueque.model.enums.TipoPeriodoEnum;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TipoPeriodoRequest {
    private Long empresaId;
    private TipoPeriodoEnum tipoPeriodoEnum;
    private LocalDate fechaInicioPeriodo;
}
