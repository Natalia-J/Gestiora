package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.SindicatoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "sindicato_empleado")
public class SindicatoEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private SindicatoEnum sindicato;
}
