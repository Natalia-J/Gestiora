package com.miproyecto.trueque.model;

import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import com.miproyecto.trueque.model.catalogs.TipoPeriodo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "reportes")
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_empleado")
    private String codigoEmpleado;

    @Column(name = "nombre_empleado")
    private String nombreEmpleado;

    @Column(name = "dias_trabajados")
    private Integer diasTrabajados;

    @Column(name = "faltas")
    private Integer faltas;

    @Column(name = "retardos")
    private Integer retardos;

    @Column(name = "salidas_tempranas")
    private Integer salidasTempranas;

    @Column(name = "horas_extras")
    private BigDecimal horasExtras;

    @Column(name = "dias_descanso_trabajados")
    private Integer diasDescansoTrabajados;

    @ManyToOne
    @JoinColumn(name = "periodo_pago_id")
    private PeriodoPago periodoPago;
}
