package com.miproyecto.trueque.model;

import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "percepciones")
public class Percepciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_empleado")
    private String codigoEmpleado;

    @ManyToOne
    @JoinColumn(name = "periodo_pago_id")
    private PeriodoPago periodoPago;

    @Column(name = "horas_extras")
    private BigDecimal horasExtras;
    @Column(name = "bono")
    private BigDecimal bono;
    @Column(name = "comisiones")
    private BigDecimal comisiones;
    @Column(name = "gratificaciones")
    private BigDecimal gratificaciones;
    @Column(name = "aguinaldo_proporcional")
    private BigDecimal aguinaldoProporcional;
    @Column(name = "prima_vacacional")
    private BigDecimal PrimaVacacional;
}
