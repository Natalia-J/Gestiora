package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.PrestacionEnum;
import com.miproyecto.trueque.model.enums.TipoPeriodoEnum;
import com.miproyecto.trueque.model.enums.TipoPrestacionEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tipo_prestacion_empleado")
public class TipoPrestacionEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private TipoPrestacionEnum tipoPrestacionEmpleado;
}
