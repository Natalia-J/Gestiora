package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "periodos_creados_empresa")
public class PeriodosCreadosEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;
    @Column(name = "fecha_terminacion", nullable = false)
    private LocalDate fechaTerminacion;
    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_periodo", nullable = false)
    private TipoPeriodo tipoPeriodo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Company empresa;
}
