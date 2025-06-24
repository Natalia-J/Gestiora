package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.CodigEmpleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "codigo_empleado_catalogo")
public class TipoCodigoEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    private CodigEmpleEnum codigo;
}
