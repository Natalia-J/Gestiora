package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.TurnoRequest;
import com.miproyecto.trueque.dto.TurnoResponse;
import com.miproyecto.trueque.model.Turno;
import com.miproyecto.trueque.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turno")
public class TurnoController {

    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<TurnoResponse> crearTurno(@RequestBody TurnoRequest request) {
        try {
            TurnoResponse turnoResponse = turnoService.crearTurnoDesdeRequest(request);
            return new ResponseEntity<>(turnoResponse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/obtener-todos")
    public ResponseEntity<List<TurnoResponse>> obtenerTodos() {
        return ResponseEntity.ok(turnoService.obtenerTodosResponse());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<TurnoResponse> obtenerPorNombre(@PathVariable String nombre) {
        return turnoService.obtenerPorNombreResponse(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/nombre/{nombre}")
    public ResponseEntity<Void> eliminarPorNombre(@PathVariable String nombre) {
        if (!turnoService.existePorNombre(nombre)) {
            return ResponseEntity.notFound().build();
        }
        turnoService.eliminarPorNombre(nombre);
        return ResponseEntity.noContent().build();
    }
}
