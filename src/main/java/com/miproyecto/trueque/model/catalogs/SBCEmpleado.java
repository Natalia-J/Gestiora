package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.SBCEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "SBC")
public class SBCEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private SBCEnum sbc;
}
