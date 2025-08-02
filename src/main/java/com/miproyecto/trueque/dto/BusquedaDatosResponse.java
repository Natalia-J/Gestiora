package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BusquedaDatosResponse {
    private String codigoEmpleado;
    private Long departamentoId;
    private Long empleadoId;

    private TurnoResponse turno;
    private PeriodoEnRegistroResponse periodo;

    private List<RegistrosDiaResponse> registros;

}
