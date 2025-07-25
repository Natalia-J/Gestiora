package com.miproyecto.trueque.model;

import com.miproyecto.trueque.model.catalogs.Inconsistencias;
import com.miproyecto.trueque.model.catalogs.Justificacion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Column(name = "horas_extras")
    private BigDecimal horasExtras;

    @Column(name = "es_dia_descanso", nullable = false)
    private Boolean esDiaDescanso = false;

    @ManyToOne
    @JoinColumn(name = "motivo_inconsistencias")
    private Inconsistencias motivoInconsistencias;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @OneToMany
    @JoinColumn(name = "justificacion_id")
    private Justificacion justificacion;

}
