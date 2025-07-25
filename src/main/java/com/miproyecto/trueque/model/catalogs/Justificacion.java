package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.JustificacionEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "justificacion")
public class Justificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private JustificacionEnum justificacion;
}
