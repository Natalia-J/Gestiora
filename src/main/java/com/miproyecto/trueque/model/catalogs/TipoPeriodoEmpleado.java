package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.model.enums.TipoPeriodoEmpleadoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "tipo_periodo_empleado")
public class TipoPeriodoEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPeriodoEmpleadoEnum tipoPeriodoEmpleado;

}
