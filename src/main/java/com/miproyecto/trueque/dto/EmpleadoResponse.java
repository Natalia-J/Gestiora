package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoResponse {
    private Long id;
    private String nombre;
    private String codigo;
}
