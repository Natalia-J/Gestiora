package com.miproyecto.trueque.model.catalogs;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "direccion_empresa")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calle_empresa", nullable = false)
    private String calleEnpresa;
    @Column(name = "num_interno")
    private String numInterno;
    @Column(name = "num_externo")
    private String numExterno;
    @Column(name = "colonia_empresa", nullable = false)
    private String coloniaEmpresa;
    @Column(name = "codigo_postal_empresa", nullable = false)
    private String codigoPostalEmpresa;
    @Column(name = "localidad_empresa")
    private String localidadEmpresa;

}
