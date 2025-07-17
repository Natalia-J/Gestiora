package com.miproyecto.trueque.model;

import ch.qos.logback.core.model.NamedModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.miproyecto.trueque.model.catalogs.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigoEmpleado;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Company empresa;

    @Column(name = "nombre_empleado", nullable = false)
    private String nombreEmpleado;
    @Column(name = "apellido_paterno_empleado", nullable = false)
    private String apellidoPaternoEmpleado;
    @Column(name = "apellido_materno_empleado", nullable = false)
    private String apellidoMaternoEmpleado;
    @Column(name = "salario_diario", nullable = false)
    private BigDecimal salarioDiario;
    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;
    @Column(name = "numero_telefono")
    private String numeroEmpleado;

    @Column(name = "sbc_parte_fija", precision = 10, scale = 2)
    private BigDecimal sbcParteFija;

    @Column(name = "sbc_parte_variable", precision = 10, scale = 2)
    private BigDecimal sbcParteVariable;

    @Column(name = "tipado_umas", precision = 10, scale = 2)
    private BigDecimal tipadoUmas;

    @Column(name = "afore_empleado")
    private String aforeEmpleado;
    @Column(name = "correo_empleado", nullable = false)
    private String correoEmpleado;
    @Column(name = "num_seguridad_social")
    private String numSeguridadSocial;
    @Column(name = "registro_patronal_imss")
    private String registroPatronalIMSS;
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimientoEmpleado;
    @Column(name = "ciudad_nacimiento")
    private String ciudadNacimientoEmpleado;
    @Column(name = "curp", nullable = false)
    private String curpEmpleado;
    @Column(name = "rfc", nullable = false)
    private String rfcEmpleado;

    @ManyToOne
    @JoinColumn(name = "puesto_id")
    private Puesto puesto;
    @ManyToOne
    @JoinColumn(name = "tipo_contrato_id")
    private TipoContratoEmpleado tipoContratoEmpleado;
    @ManyToOne
    @JoinColumn(name = "tipo_periodo_id")
    private TipoPeriodo tipoPeriodo;
    @ManyToOne
    @JoinColumn(name = "base_cotizacion_id")
    private BaseCotizacionEmpleado baseCotizacion;
    @ManyToOne
    @JoinColumn(name = "sindicato_id")
    private SindicatoEmpleado sindicatoEmpleado;
    @ManyToOne
    @JoinColumn(name = "tipo_prestacion_id")
    private TipoPrestacionEmpleado tipoPrestacionEmpleado;
    @ManyToOne
    @JoinColumn(name = "metodo_pago_id")
    private MetodoPagoEmpleado metodoPagoEmpleado;
    @OneToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccionEmployee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamentoEmple;
    @ManyToOne
    @JoinColumn(name = "zona_salario_id")
    private ZonaSalarioGeneral zonaSalario;
    @ManyToOne
    @JoinColumn(name = "base_de_pago_id")
    private BaseDePago baseDePago;
    @ManyToOne
    @JoinColumn(name = "turno_id")
    private Turno turno;
    @ManyToOne
    @JoinColumn(name = "regimen_id")
    private RegimenEmployee regimenEmployee;
    @ManyToOne
    @JoinColumn(name = "genero_id")
    private GeneroEmpleado genero;
    @ManyToOne
    @JoinColumn(name = "estado_civil_id")
    private EstadoCivilEmpleado estadoCivil;
    @ManyToOne
    @JoinColumn(name = "entidad_federativa_id")
    private EntidadFederativa entidadFederativa;
}
