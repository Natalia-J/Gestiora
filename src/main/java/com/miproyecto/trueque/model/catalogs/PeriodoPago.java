package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "periodo_pago")
public class PeriodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Company empresa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_periodo_empleado_id")
    private TipoPeriodoEmpleado tipoPeriodoEmpleado;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false)
    private boolean cerrado = false;

}
