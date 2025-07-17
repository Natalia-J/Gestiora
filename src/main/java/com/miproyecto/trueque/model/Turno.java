package com.miproyecto.trueque.model;

import com.miproyecto.trueque.model.catalogs.DiaSemana;
import com.miproyecto.trueque.model.catalogs.TipoJornada;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "turno")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_puesto", nullable = false)
    private String nombre;
    @Column(name = "hora_entrada", nullable = false)
    private String horaEntrada;
    @Column(name = "hora_salida", nullable = false)
    private String horaSalida;
    @Column(name = "horas_turno", nullable = false)
    private double horasTurno;
    @ManyToOne
    @JoinColumn(name = "tipo_jornada", nullable = false)
    private TipoJornada tipoJornada;

    @ManyToMany
    @JoinTable(
            name = "turno_dias_descanso",
            joinColumns = @JoinColumn(name = "turno_id"),
            inverseJoinColumns = @JoinColumn(name = "dia_semana_id")
    )
    private Set<DiaSemana> diasDescanso;


}
