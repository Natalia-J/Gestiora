package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.enums.EntidadFederativaEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;
@Setter
@Getter
@Entity
@Table(name = "entidad_federativa")
public class EntidadFederativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EntidadFederativaEnum entidadFederativa;

}
