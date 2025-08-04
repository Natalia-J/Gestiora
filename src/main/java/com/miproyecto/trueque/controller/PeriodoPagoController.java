package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.PeriodoEmpleadoResponseBin;
import com.miproyecto.trueque.dto.PeriodoPagoRequest;
import com.miproyecto.trueque.dto.PeriodoPagoResponse;
import com.miproyecto.trueque.service.PeriodoPagoEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/periodo-pago")
public class PeriodoPagoController {

    private final PeriodoPagoEmpleadoService periodoPagoEmpleadoService;

    @PostMapping("/generar")
    public ResponseEntity<List<PeriodoPagoResponse>> generarPeriodos(@RequestBody List<PeriodoPagoRequest> solicitudes) {
        List<PeriodoPagoResponse> generados = periodoPagoEmpleadoService.generarPeriodosPago(solicitudes);
        return ResponseEntity.ok(generados);
    }

    @GetMapping("/empleado/{tipoPeriodo}")
    public ResponseEntity<?> periodoEmpleado(@PathVariable Long tipoPeriodo) {
        List<PeriodoEmpleadoResponseBin> generados = periodoPagoEmpleadoService.getPeriodosEmpleado(tipoPeriodo);
        return ResponseEntity.ok(generados);
    }

}
