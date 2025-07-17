package com.miproyecto.trueque.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "El nombre de la empresa no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre de la empresa debe tener entre 2 y 100 caracteres")
    private String nombre;
    private String mascarilla;
    private int vigencia;
    private LocalDate fecha_inicio;
    @Min(value = 0, message = "El factor no deducible debe ser mayor o igual a 0")
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
