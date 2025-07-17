package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.model.Departamento;
import com.miproyecto.trueque.service.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService){
        this.departamentoService = departamentoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Departamento> crear(@RequestBody Departamento depto) {
        Departamento creado = departamentoService.guardar(depto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/listar")
    public List<Departamento> listar() {
        return departamentoService.listar();
    }

    @GetMapping("/codigo/{codigo}")
    public Departamento buscarPorCodigo(@PathVariable String codigo) {
        return departamentoService.buscarPorCodigo(codigo);
    }

    @GetMapping("/tieneEmpleados/{id}")
    public ResponseEntity<Boolean> tieneEmpleados(@PathVariable Long id) {
        boolean tiene = departamentoService.existenEmpleadosEnDepartamento(id);
        return ResponseEntity.ok(tiene);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            departamentoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminarConEmpleados/{id}")
    public ResponseEntity<?> eliminarConEmpleados(@PathVariable Long id) {
        try {
            departamentoService.eliminarDepartamentoYEmpleados(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
