package com.miproyecto.trueque.model.catalogs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name = "Representante_legal")
public class RepresentanteLegal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_representante")
    private String nameRepresentante;

    @Column( name = "apellido_paterno_representante", nullable = false)
    private String apellidoPatRepresentante;

    @Column( name = "apellido_materno_representante", nullable = false)
    private String apellidoMatRepresentante;
}
