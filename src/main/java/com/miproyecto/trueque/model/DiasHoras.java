package com.miproyecto.trueque.model;

import com.miproyecto.trueque.model.catalogs.Inconsistencias;
import com.miproyecto.trueque.model.catalogs.TipoPeriodo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name = "dias_horas")
public class DiasHoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departamento", nullable = false)
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "empleado", nullable = false)
    private Employee empleado;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;

    @Column(name = "hora_salida")
    private LocalTime horaSalida;


    @Column(name = "horas_trabajadas")
    private BigDecimal horasReales;

    @Column(name = "horas_dobles")
    private BigDecimal horasDobles;

    @Column(name = "horas-triples")
    private BigDecimal horasTriples;

    @ManyToOne
    @JoinColumn(name = "inconsistencias")
    private Inconsistencias inconsistencias;

    @ManyToOne
    @JoinColumn(name = "motivo_inconsistencias")
    private Inconsistencias motivoInconsistencias;

    @Column(name = "feriado")
    private boolean fereado;

    @Column(name = "descanso")
    private boolean descanso;

    //datos que se podrian guardar para auditoria
    @ManyToOne
    @JoinColumn(name = "periodo", nullable = false)
    private TipoPeriodo tipoPeriodo;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

    @Column(name = "creado_por")
    private String creadoPor;

    @Column(name = "actualizado_por")
    private String actualizadoPor;


    @PrePersist
    public void prePersist() {
        this.creadoEn = LocalDateTime.now();
        this.actualizadoEn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.actualizadoEn = LocalDateTime.now();
    }

}
