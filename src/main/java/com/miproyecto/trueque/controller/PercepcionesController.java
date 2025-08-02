package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.FiltroPeriodoRequest;
import com.miproyecto.trueque.dto.PercepcionesRequest;
import com.miproyecto.trueque.dto.PercepcionesResponse;
import com.miproyecto.trueque.service.PercepcionesService;
import com.miproyecto.trueque.service.ReporteService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/percepciones")
@RequiredArgsConstructor
@Slf4j
public class PercepcionesController {
    private final PercepcionesService percepcionesService;

    @GetMapping
    public ResponseEntity<?> obtenerPercepciones(@RequestParam Long tipoPeriodoId,
                                                 @RequestParam(required = false) Long periodoId) {
        try {
            List<PercepcionesResponse> percepciones = percepcionesService.obtenerPercepciones(tipoPeriodoId, periodoId);

            if (percepciones.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(percepciones);
        } catch (IllegalArgumentException e) {
            log.warn("Error en la petición de percepciones: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Solicitud inválida: " + e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al obtener percepciones", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PostMapping("/{codigoEmpleado}/periodo/{periodoId}")
    public ResponseEntity<?> guardarPercepciones(@PathVariable String codigoEmpleado,
                                                 @PathVariable Long periodoId,
                                                 @RequestBody PercepcionesRequest request) {
        try {
            percepcionesService.guardarPercepciones(codigoEmpleado, periodoId, request);
            return ResponseEntity.ok("Percepciones guardadas correctamente");
        } catch (RuntimeException e) {
            log.error("Error al guardar percepciones", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al guardar percepciones", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}
