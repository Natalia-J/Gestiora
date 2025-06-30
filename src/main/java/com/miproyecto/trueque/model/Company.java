package com.miproyecto.trueque.model;

import com.miproyecto.trueque.model.catalogs.Direccion;
import com.miproyecto.trueque.model.catalogs.RegimenFiscal;
import com.miproyecto.trueque.model.catalogs.TipoCodigoEmpleado;
import com.miproyecto.trueque.model.catalogs.ZonaSalarioGeneral;
import com.miproyecto.trueque.model.enums.RegimenFiscalCompanyEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre_empresa", unique = true, nullable = false)
    private String nombre;
    @Column(name = "mascarilla_codigo_empleado", nullable = false)
    private String mascarilla;
    @Column(name = "ejercicio_vigente", nullable = false)
    private int vigencia;
    @Column(name = "fecha_inicio_historial", nullable = false)
    private LocalDate fecha_inicio;
    @Column(name = "factor_no_deducible", nullable = false)
    private double factor;
    @Column( name = "telefono_1", nullable = false)
    private String telefono1;
    @Column( name = "telefono2", nullable = false)
    private String telefono2;
    @Column( name = "registro_patronal")
    private String patronalIMSS;
    @Column( name = "RFC", nullable = false)
    private String rfc;
    @Column(name = "registro_infonavit_empresa")
    private String infonavit;
    @Column(name = "nombre_representante", nullable = false)
    private String nombreRepresentante;
    @Column(name = "apellido_Pa_representante", nullable = false)
    private String apellidoPaRepresentante;
    @Column(name = "apellido_Ma_representante", nullable = false)
    private String apellidoMaRepresentante;
    @ManyToOne
    @JoinColumn(name = "zona_salarial_id")
    private ZonaSalarioGeneral zonaSalario;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "direccion_id")
    private Direccion direccionCompany;
    @ManyToOne
    @JoinColumn(name = "tipo_codigo_id")
    private TipoCodigoEmpleado tipoCodigoEmpleado;
    @ManyToOne
    @JoinColumn(name = "regimen_fiscal_id")
    private RegimenFiscal regimenFiscalCompany;
}
