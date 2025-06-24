package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.TipoContratoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tipo_contrato_empleado")
public class TipoContratoEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private TipoContratoEnum contrato;
}
