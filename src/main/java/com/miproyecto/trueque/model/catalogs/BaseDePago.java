package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.BasePagoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "base_de_pago")
public class BaseDePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private BasePagoEnum baseDePago;
}
