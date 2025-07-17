package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.TipoPeriodoRequest;
import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.catalogs.TipoPeriodo;
import com.miproyecto.trueque.service.EmpresaService;
import com.miproyecto.trueque.service.TipoPeriodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tipo-periodo")
public class TipoPeriodoController {

    private TipoPeriodoService tipoPeriodoService;
    private EmpresaService empresaService;

    public TipoPeriodoController(TipoPeriodoService tipoPeriodoService, EmpresaService empresaService){
        this.tipoPeriodoService=tipoPeriodoService;
        this.empresaService = empresaService;
    }

    @PostMapping("/crear-inicial")
    public ResponseEntity<TipoPeriodo> crearPeriodoInicial(@RequestBody TipoPeriodoRequest request) {
        Company empresa = empresaService.obtenerEmpresaPorID(request.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        TipoPeriodo nuevoPeriodo = tipoPeriodoService.crearPeriodoInicial(
                empresa,
                request.getTipoPeriodoEnum(),
                request.getFechaInicioPeriodo()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPeriodo);
    }

}
