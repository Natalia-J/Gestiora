package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.FiltroPeriodoRequest;
import com.miproyecto.trueque.dto.PrenominaRequest;
import com.miproyecto.trueque.dto.PrenominaResponse;
import com.miproyecto.trueque.service.NominaExportService;
import com.miproyecto.trueque.service.PrenominaService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenomina")
public class PrenominaController {
    private final NominaExportService nominaExportService;
    private final PrenominaService prenominaService;

    public PrenominaController(PrenominaService prenominaService, NominaExportService nominaExportService) {
        this.prenominaService = prenominaService;
        this.nominaExportService = nominaExportService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<PrenominaResponse>> listarPrenominaPorTipoPeriodo(
            @RequestParam Long tipoPeriodoId) {
        List<PrenominaResponse> prenominaList = prenominaService.generarPrenominaPorTipoPeriodo(tipoPeriodoId);
        return ResponseEntity.ok(prenominaList);
    }

    @PostMapping("/guardar/{empleadoId}")
    public ResponseEntity<Void> guardarPrenomina(
            @PathVariable Long empleadoId,
            @RequestParam Long tipoPeriodoId,
            @RequestBody PrenominaRequest request) {
        prenominaService.guardarPrenomina(empleadoId, tipoPeriodoId, request);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/export/pdf/{employeeId}")
    public ResponseEntity<byte[]> exportNominaPdf(@PathVariable Long employeeId) {
        try {
            byte[] pdfBytes = nominaExportService.exportPdf(employeeId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "nomina_empleado_" + employeeId + ".pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/export/pdf/download/{employeeId}")
    public ResponseEntity<byte[]> downloadNominaPdf(@PathVariable Long employeeId) {
        try {
            byte[] pdfBytes = nominaExportService.exportPdf(employeeId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentLength(pdfBytes.length);
            headers.set("Content-Disposition", "attachment; filename=\"nomina_empleado_" + employeeId + ".pdf\"");
            headers.setCacheControl("no-cache, no-store, must-revalidate");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
