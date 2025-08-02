package com.miproyecto.trueque.model.catalogs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@RequiredArgsConstructor
public class TramoISR {
    private final BigDecimal limiteInferior;
    private final BigDecimal limiteSuperior;
    private final BigDecimal cuotaFija;
    private final BigDecimal porcentajeSobreExcedente;

    public TramoISR(String li, String ls, String cf, String porcentaje) {
        this.limiteInferior = new BigDecimal(li);
        this.limiteSuperior = (ls != null) ? new BigDecimal(ls) : null;
        this.cuotaFija = new BigDecimal(cf);
        this.porcentajeSobreExcedente = new BigDecimal(porcentaje);
    }

}
