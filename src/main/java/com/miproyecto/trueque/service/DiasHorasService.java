package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.BusquedaDatosRequest;
import com.miproyecto.trueque.dto.BusquedaDatosResponse;
import com.miproyecto.trueque.dto.TurnoResponse;
import com.miproyecto.trueque.model.DiasHoras;
import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.repository.DepartamentoRepository;
import com.miproyecto.trueque.repository.DiasHorasRepository;
import com.miproyecto.trueque.repository.EmployeeRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Getter
@Service
@RequiredArgsConstructor
public class DiasHorasService {
    private final DiasHorasRepository diasHorasRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartamentoRepository departamentoRepository;
    private final TurnoService turnoService;


    public BusquedaDatosResponse buscarDatos(BusquedaDatosRequest request){
        Employee empleado = null;

        if (request.getCodigoEmpleado() != null && !request.getCodigoEmpleado().isEmpty()) {
            empleado = employeeRepository.findByCodigoEmpleado(request.getCodigoEmpleado())
                    .orElseThrow(() -> new RuntimeException("No existe empleado"));
        }
        else if(request.getEmpleadoId() != null && request.getDepartamentoId() != null){
            empleado = employeeRepository.findById(request.getEmpleadoId())
                    .orElseThrow(()-> new RuntimeException("Empleado no encontrado"));
            if (!empleado.getDepartamentoEmple().getId().equals(request.getDepartamentoId())){
                throw  new RuntimeException("empleado no pertenece a departamento");
            }
        }else{
            throw  new RuntimeException("parametros insuficientes para la busqueda");
        }

        List<DiasHoras> diasHorasList= diasHorasRepository.findByEmpleadoId(empleado.getId());

        Long periodos =0L;
        Long registro =0L;

        TurnoResponse turnoResponse = null;
        if(empleado.getTurno() != null){
            turnoResponse = turnoService.mapearATurnoResponse(empleado.getTurno());
        }

        return new BusquedaDatosResponse(
                empleado.getCodigoEmpleado(),
                empleado.getDepartamentoEmple().getId(),
                empleado.getId(),
                turnoResponse,
                periodos,
                registro
        );
    }
}












