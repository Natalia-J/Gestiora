package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.CatalogsResponse;
import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    private CatalogService catalogService;

    public CatalogController(CatalogService catalogService){
        this.catalogService = catalogService;
    }

    @GetMapping("/catalogos")
    public ResponseEntity<CatalogsResponse> listarCatalogos(){
        return ResponseEntity.ok(catalogService.getAllCatalogs());
    }

    @GetMapping("/inconsistencias")
    public ResponseEntity<?> listarInconsistencias(){
        return ResponseEntity.ok(catalogService.getInconsistencias());
    }


    @GetMapping("/periodo")
    public ResponseEntity<?> listarPeriodos(){
        return ResponseEntity.ok(catalogService.getTipoPeriodos());
    }


}