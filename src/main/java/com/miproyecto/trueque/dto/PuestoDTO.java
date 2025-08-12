package com.miproyecto.trueque.dto;

import com.miproyecto.trueque.model.Puesto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PuestoDTO {
    private String nombre;

    public PuestoDTO(Puesto puesto) {
        this.nombre = puesto.getNombre();
    }
}
