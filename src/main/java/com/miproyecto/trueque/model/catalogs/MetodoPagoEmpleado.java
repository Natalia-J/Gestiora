package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.MetodoPagoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "metodo_pago_empleado")
public class MetodoPagoEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private MetodoPagoEnum metodoPago;
}
