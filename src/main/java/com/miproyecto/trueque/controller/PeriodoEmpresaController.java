package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.PeriodoEmpleadoResponseBin;
import com.miproyecto.trueque.dto.PeriodoEmpresaRequest;
import com.miproyecto.trueque.dto.PeriodoEmpresaResponse;
import com.miproyecto.trueque.interceptor.EmpresaContextHolder;
import com.miproyecto.trueque.model.catalogs.PeriodosCreadosEmpresa;
import com.miproyecto.trueque.service.PeriodoEmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periodo-empresa")
@RequiredArgsConstructor
public class PeriodoEmpresaController {

    private final PeriodoEmpresaService periodoEmpresaService;

    @PostMapping("/crear")
    public ResponseEntity<PeriodoEmpresaResponse> crearPeriodo(
            @RequestBody PeriodoEmpresaRequest request) {

        Long empresaId = EmpresaContextHolder.getEmpresaId();

        PeriodosCreadosEmpresa periodo = periodoEmpresaService.crearOActualizarPeriodo(
                empresaId,
                request.getPeriodoEmpresaId(),
                request.getFechaInicio());

        return ResponseEntity.ok(periodoEmpresaService.mapToResponse(periodo));
    }

    @GetMapping("/list")
    public ResponseEntity<List<PeriodoEmpleadoResponseBin>> listPeriodoEmpresa() {
        return ResponseEntity.ok(periodoEmpresaService.getPeriodoEmpresas(EmpresaContextHolder.getEmpresaId()));
    }


}


