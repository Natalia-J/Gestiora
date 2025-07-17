package com.miproyecto.trueque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private Long empresaId;
    private String codigoEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaAlta;
    private Long tipoContrato;
    private Long tipoDepartamento;
    private Long tipoPeriodo;
    private BigDecimal salarioDiario;
    private Long baseCotizacion;

    private BigDecimal sbcParteFija;
    private BigDecimal sbcParteVariable;
    private BigDecimal tipadoUmas;

    private Long departamento;
    private Long puesto;
    private Long sindicato;
    private Long tipoPrestacion;
    private Long baseDePago;
    private Long metodoPago;
    private Long turnoTrabajo;
    private Long zonaSalario;
    private Long tipoRegimen;
    private String afore;
    private String correo;
    private String numTelefono;
    private String numSeguridadSocial;
    private String registroPatronalImss;
    private Long estadoCivil;
    private Long genero;
    private LocalDate fechaNacimiento;
    private Long entidadFederativa;
    private String ciudad;
    private String curp;
    private String rfc;
    private DireccionRequest direccion;
    private Long avisosPendientesImss;
}
