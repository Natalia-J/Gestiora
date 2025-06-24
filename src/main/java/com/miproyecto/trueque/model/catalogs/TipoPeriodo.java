package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.TipoPeriodoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tipo_periodo_empleado")
public class TipoPeriodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private TipoPeriodoEnum periodo;
}
