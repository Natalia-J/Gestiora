package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.RegimenFiscalCompanyEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "regimen_fiscal")
public class RegimenFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private RegimenFiscalCompanyEnum regimenEmpresa;
}
