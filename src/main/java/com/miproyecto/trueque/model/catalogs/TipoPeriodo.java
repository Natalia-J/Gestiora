package com.miproyecto.trueque.model.catalogs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.enums.TipoPeriodoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "tipo_periodo_company")
public class TipoPeriodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPeriodoEnum periodo;

    @Column(name = "fecha_inicio_periodo", nullable = false)
    private LocalDate fechaInicioPeriodo;

    @Column(name = "fecha_fin_periodo", nullable = false)
    private LocalDate fechaFinPeriodo;

    @Column(nullable = false)
    private boolean cerrado = false;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    @JsonBackReference
    private Company empresa;
}
