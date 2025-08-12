package com.miproyecto.trueque.model;

import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "prenomina")
public class Prenomina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Employee empleado;

    @Column(name = "sueldo_base", nullable = false)
    private BigDecimal sueldoBase;
    //percepciones
    @Column(name = "horas_extras", nullable = false)
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
    //deducciones
    @Column(name = "ISR")
    private BigDecimal ISR;
    @Column(name = "IMSS")
    private BigDecimal IMSS;
    @Column(name = "INFONAVIT")
    private BigDecimal INFONAVIT;
    @Column(name = "otras_deducciones")
    private BigDecimal otrasDeducciones;

    @Column(name = "total_neto")
    private Double totalNeto;

    @ManyToOne
    @JoinColumn(name = "periodo_pago_id", nullable = false)
    private PeriodoPago periodoPago;

}
