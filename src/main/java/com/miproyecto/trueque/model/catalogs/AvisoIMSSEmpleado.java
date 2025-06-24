package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.AvisoIMSSEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "aviso_imss_empleado")
public class AvisoIMSSEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private AvisoIMSSEnum avisoImssEmpleado;

}
