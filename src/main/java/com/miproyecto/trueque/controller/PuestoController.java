package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.model.Puesto;
import com.miproyecto.trueque.service.PuestoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puestos")
public class PuestoController {

    private final PuestoService puestoService;

    public PuestoController(PuestoService puestoService) {
        this.puestoService = puestoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Puesto> crear(@RequestBody Puesto puesto) {
        return ResponseEntity.ok(puestoService.guardar(puesto));
    }

    @GetMapping("/obtener-todos")
    public ResponseEntity<List<Puesto>> obtenerTodos() {
        return ResponseEntity.ok(puestoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Puesto> obtenerPorId(@PathVariable Long id) {
        return puestoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        puestoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
