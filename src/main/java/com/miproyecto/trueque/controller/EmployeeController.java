package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.EmpleadoResponse;
import com.miproyecto.trueque.dto.EmployeeObtenerResponse;
import com.miproyecto.trueque.dto.EmployeeRequest;
import com.miproyecto.trueque.dto.MoverEmpleadoRequest;
import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.service.EmployeeService;
import com.miproyecto.trueque.service.PeriodoPagoEmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleado")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PeriodoPagoEmpleadoService periodoPagoEmpleadoService;

    public EmployeeController(EmployeeService employeeService, PeriodoPagoEmpleadoService periodoPagoEmpleadoService){
        this.employeeService=employeeService;
        this.periodoPagoEmpleadoService = periodoPagoEmpleadoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Employee> getEmpleadoPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(employeeService.getByCodigoEmpleado(codigo));
    }

    @PostMapping("/crear")
    public ResponseEntity<Employee> crearEmpleado(@RequestBody EmployeeRequest request) {
        Employee nuevoEmpleado = employeeService.createEmployee(request);
        return ResponseEntity.ok(nuevoEmpleado);
    }

    @GetMapping("departamento/{id}/tiene-empleados")
    public ResponseEntity<Boolean> tieneEmpleados(@PathVariable Long id) {
        boolean tiene = employeeService.existenEmpleadosEnDepartamento(id);
        return ResponseEntity.ok(tiene);
    }

    @PutMapping("/mover-departamento")
    public ResponseEntity<String> moverEmpleado(@RequestBody MoverEmpleadoRequest request) {
        employeeService.moverEmpleadoADepartamento(
                request.getEmpleadoId(), request.getNuevoDepartamentoId()
        );
        return ResponseEntity.ok("Empleado movido correctamente");
    }

    @GetMapping("/{tipoPeriodo}")
    public ResponseEntity<List<EmpleadoResponse>> obtenerEmpleadosPorTipoPeriodo(@PathVariable Long tipoPeriodo) {
        List<EmpleadoResponse> empleados = employeeService.obtenerEmpleadosPorTipoPeriodo(tipoPeriodo);
        return ResponseEntity.ok(empleados);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Employee> editarEmpleado(@PathVariable Long id, @RequestBody EmployeeRequest request) {
        Employee actualizado = employeeService.updateEmployee(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<EmployeeObtenerResponse> obtenerEmpleado(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.obtenerEmpleadoPorId(id));
    }

}
