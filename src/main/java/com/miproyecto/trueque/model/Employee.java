package com.miproyecto.trueque.model;

import ch.qos.logback.core.model.NamedModel;
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

    @Column(name = "nombre_empleado", nullable = false)
    private String nombreEmpleado;
    @Column(name = "apellido_empleado", nullable = false)
    private String apellidoPaternoEmpleado;
    @Column(name = "apellido_materno_empleado", nullable = false)
    private String apellidoMaternoEmpleado;
    @Column(name = "salario_diario", nullable = false)
    private BigDecimal salarioDiario;
    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;
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
    @Column(name = "ciudad_nacimineto")
    private String ciudadNacimientoEmpleado;
    @Column(name = "curp", nullable = false)
    private String curpEmpleado;
    @Column(name = "rfc", nullable = false)
    private String rfcEmpleado;
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
    @JoinColumn(name = "sindicato_is")
    private SindicatoEmpleado sindicatoEmpleado;
    @ManyToOne
    @JoinColumn(name = "tipo_prestamo_id")
    private TipoPrestacionEmpleado tipoPrestacionEmpleado;
    @ManyToOne
    @JoinColumn(name = "metodo_pago_id")
    private MetodoPagoEmpleado metodoPagoEmpleado;
    @OneToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccionEmployee;
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

}
