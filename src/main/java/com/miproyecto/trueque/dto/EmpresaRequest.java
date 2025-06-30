package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaRequest {
    private String nombre;
    private String mascarilla;
    private int vigencia;
    private LocalDate fecha_inicio;
    private double factor;
    private String telefono1;
    private String telefono2;
    private String patronalIMSS;
    private String rfc;
    private String infonavit;
    private String nombreRepresentante;
    private String apellidoPaRepresentante;
    private String apellidoMaRepresentante;

    private Long zonaSalarioId;
    private Long tipoCodigoEmpleadoId;
    private Long regimenFiscalCompanyId;

    private DireccionRequest direccionCompany;

}
