package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.EstadoCivilEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estado_civil")
public class EstadoCivilEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EstadoCivilEnum estadoCivilEmpleado;
}
