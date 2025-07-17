package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.TipoContratoEnum;
import com.miproyecto.trueque.model.enums.TipoJornadaEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name = "tipo_jornada")
public class TipoJornada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private TipoJornadaEnum tipoJornada;

    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Double duracionMaxima;

}
