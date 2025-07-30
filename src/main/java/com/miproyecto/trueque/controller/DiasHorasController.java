package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.BusquedaDatosRequest;
import com.miproyecto.trueque.dto.BusquedaDatosResponse;
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
    public ResponseEntity<BusquedaDatosResponse> busquedaDatos(@RequestBody BusquedaDatosRequest request){
        try {
            BusquedaDatosResponse response = diasHorasService.buscarDatos(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            System.err.println("Error en service: " + ex.getMessage());
            return  ResponseEntity.badRequest().body(null);
        }
    }
}