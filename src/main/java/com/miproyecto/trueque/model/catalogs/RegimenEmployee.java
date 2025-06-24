package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.RegimenEmployeeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "regimen_empleado")
public class RegimenEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private RegimenEmployeeEnum regimenFiscalEmployee;
}
