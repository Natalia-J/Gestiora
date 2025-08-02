package com.miproyecto.trueque.model;

import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class PeriodoEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Employee empleado;

    @ManyToOne
    @JoinColumn(name = "periodo_pago_id")
    private PeriodoPago periodoPago;

    private boolean activo;
}
