package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusquedaDatosRequest {
    private String codigoEmpleado;
    private Long departamentoId;
    private Long empleadoId;
}
