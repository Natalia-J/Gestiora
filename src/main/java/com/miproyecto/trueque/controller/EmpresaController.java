package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.EmpresaRequest;
import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.service.EmpresaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/empresa")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService){
        this.empresaService = empresaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Company> crearEmpresa(@Valid @RequestBody EmpresaRequest empresaRequest){
        log.info(empresaRequest.toString());
        Company nuevaEmpresa = empresaService.crearEmpresa(empresaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEmpresa);
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<Company>> obtenerEmpresas(){
        List<Company> empresas = empresaService.obtenerEmpresa();
        return ResponseEntity.ok(empresas);
    }


    /*@PostMapping("/crear")
    public ResponseEntity<EmpresaResponse> crearEmpresa(@RequestBody EmpresaRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.crearEmpresa(dto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EmpresaResponse>> listarEmpresas() {
        return ResponseEntity.ok(empresaService.obtenerTodasLasEmpresas());
    }*/



}
