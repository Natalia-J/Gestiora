package com.miproyecto.trueque.model.catalogs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "departamentos")
public class DepartamentosEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre_departamento", nullable = false)
    private String nombreDepartamento;
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcionDepartamento;



}
