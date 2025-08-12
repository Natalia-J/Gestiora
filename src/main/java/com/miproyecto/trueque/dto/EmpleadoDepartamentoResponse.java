package com.miproyecto.trueque.dto;

import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.model.Turno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDepartamentoResponse {
    private Long id;
    private String codigoEmpleado;
    private String nombreEmpleado;
    private String apellidoPaternoEmpleado;
    private String apellidoMaternoEmpleado;
    private PuestoDTO puesto;

    public EmpleadoDepartamentoResponse(Employee empleado) {
        this.id = empleado.getId();
        this.codigoEmpleado = empleado.getCodigoEmpleado();
        this.nombreEmpleado = empleado.getNombreEmpleado();
        this.apellidoPaternoEmpleado = empleado.getApellidoPaternoEmpleado();
        this.apellidoMaternoEmpleado = empleado.getApellidoMaternoEmpleado();
        if (empleado.getPuesto() != null) {
            this.puesto = new PuestoDTO(empleado.getPuesto());
        }
    }
}
