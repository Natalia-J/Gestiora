package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DireccionRequest {
    private String calle;
    private String numInterno;
    private String numExterno;
    private String colonia;
    private String codigoPostal;
    private String localidad;
}
