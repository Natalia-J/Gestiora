package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.BusquedaDatosRequest;
import com.miproyecto.trueque.dto.BusquedaDatosResponse;
import com.miproyecto.trueque.dto.GuardarRegistroRequest;
import com.miproyecto.trueque.service.DiasHorasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dias-horas")
@RequiredArgsConstructor
public class DiasHorasController {

    private final DiasHorasService diasHorasService;

    @PostMapping("/buscar")
    public ResponseEntity<BusquedaDatosResponse> buscarDatos(@RequestBody BusquedaDatosRequest request) {
        BusquedaDatosResponse response = diasHorasService.buscarDatos(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRegistro(@RequestBody GuardarRegistroRequest request) {
        try {
            diasHorasService.guardarRegistro(request);
            return ResponseEntity.ok("Registro guardado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar registro: " + e.getMessage());
        }
    }

}