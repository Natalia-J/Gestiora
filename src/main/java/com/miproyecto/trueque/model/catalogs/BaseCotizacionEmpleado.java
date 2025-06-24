package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.BaseCotizacionEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "base_cotizacion_empleado")
public class BaseCotizacionEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private BaseCotizacionEnum baseCotizacion;

    @ManyToOne
    @JoinColumn(name = "sbc_empleado_id")
    private SBCEmpleado sbcEmpleado;
}
