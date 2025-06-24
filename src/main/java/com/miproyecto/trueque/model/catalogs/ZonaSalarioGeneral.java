package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.ZonaSalarioEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "zona_salario")
public class ZonaSalarioGeneral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated( value = EnumType.STRING)
    private ZonaSalarioEnum zonaSalario;
}
