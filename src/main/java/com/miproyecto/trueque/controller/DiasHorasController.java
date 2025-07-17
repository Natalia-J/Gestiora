package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.HorasDiasRequest;
import com.miproyecto.trueque.model.DiasHoras;
import com.miproyecto.trueque.service.DiasHorasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dias-horas")
public class DiasHorasController {

    private final DiasHorasService diasHorasService;

    public DiasHorasController(DiasHorasService diasHorasService){
        this.diasHorasService=diasHorasService;
    }

    @PostMapping
    public ResponseEntity<?> crearRegistro(@Valid @RequestBody HorasDiasRequest request) {
        try {
            DiasHoras registro = diasHorasService.registrarDiaHora(request);
            return ResponseEntity.ok(registro);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }

    @GetMapping("/periodo/{tipoPeriodoId}")
    public ResponseEntity<List<DiasHoras>> obtenerRegistrosPorPeriodo(@PathVariable Long tipoPeriodoId) {
        List<DiasHoras> registros = diasHorasService.obtenerRegistrosPorPeriodo(tipoPeriodoId);
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> existeRegistro(
            @RequestParam Long empleadoId,
            @RequestParam String fecha) {
        LocalDate fechaConsulta = LocalDate.parse(fecha);
        boolean existe = diasHorasService.verificarSiExisteRegistro(empleadoId, fechaConsulta);
        return ResponseEntity.ok(existe);
    }

}
