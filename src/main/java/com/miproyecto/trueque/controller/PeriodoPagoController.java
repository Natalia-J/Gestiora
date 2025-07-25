package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.PeriodoPagoRequest;
import com.miproyecto.trueque.dto.PeriodoPagoResponse;
import com.miproyecto.trueque.interceptor.EmpresaContextHolder;
import com.miproyecto.trueque.service.PeriodoEmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periodo-pago")
public class PeriodoPagoController {

    private final PeriodoEmpleadoService periodoEmpleadoService;

    public PeriodoPagoController(PeriodoEmpleadoService periodoEmpleadoService){
        this.periodoEmpleadoService=periodoEmpleadoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<PeriodoPagoResponse> crearPeriodo(@RequestBody PeriodoPagoRequest request) {
        PeriodoPagoResponse response = periodoEmpleadoService.crearPeriodoPago(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/generar-hasta-fin-anio")
    public ResponseEntity<List<PeriodoPagoResponse>> generarPeriodosHastaFinDeAnio(@RequestBody PeriodoPagoRequest request) {
        List<PeriodoPagoResponse> periodos = periodoEmpleadoService.generarPeriodosHastaFinDeAnio(request);
        return ResponseEntity.ok(periodos);
    }

    @PutMapping("/{id}/cerrar")
    public ResponseEntity<Void> cerrarPeriodo(@PathVariable Long id) {
        periodoEmpleadoService.cerrarPeriodo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ultimo-abierto/{empleadoId}")
    public ResponseEntity<PeriodoPagoResponse> getUltimoPeriodoAbierto(@PathVariable Long empleadoId) {
        Long empresaId = EmpresaContextHolder.getEmpresaId(); // Tomamos la empresa actual del contexto
        PeriodoPagoResponse response = periodoEmpleadoService.getUltimoPeriodoAbiertoPorEmpresa(empleadoId, empresaId);
        return ResponseEntity.ok(response);
    }

}
