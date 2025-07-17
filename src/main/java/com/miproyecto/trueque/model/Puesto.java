package com.miproyecto.trueque.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "puesto")
public class Puesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_puesto", nullable = false)
    private String nombre;
    @Column(name = "descripcion_puesto")
    private String descripcionPuesto;
}
