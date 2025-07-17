package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogsResponse {
    private List<GenericCatalogResponse> imssEmpleado;
    private List<GenericCatalogResponse> sbcEmpleado;
    private List<GenericCatalogResponse> entidadFederativa;
    private List<GenericCatalogResponse> estadoCivil;
    private List<GenericCatalogResponse> genero;
    private List<GenericCatalogResponse> metodoPagoEmpleado;
    private List<GenericCatalogResponse> regimenEmpleado;
    private List<GenericCatalogResponse> regimenEmpresa;
    private List<GenericCatalogResponse> sindicatoEmpleado;
    private List<GenericCatalogResponse> tipoCodigoEmpleado;
    private List<GenericCatalogResponse> tipoContratoEmpleado;
    private List<GenericCatalogResponse> tipoPeriodo;
    private List<GenericCatalogResponse> tipoPrestacionEmpleado;
    private List<GenericCatalogResponse> zonaSalarioGeneral;
    private List<GenericCatalogResponse> tipoJornada;
    private List<GenericCatalogResponse> baseDePago;
    private List<GenericCatalogResponse> diaSemana;

}
