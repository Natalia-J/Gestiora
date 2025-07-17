package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.InconsistenciasEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "inconsistencias")
public class Inconsistencias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private InconsistenciasEnum inconsistencias;
}
