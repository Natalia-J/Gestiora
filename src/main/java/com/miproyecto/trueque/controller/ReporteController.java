package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.ReporteRequest;
import com.miproyecto.trueque.dto.ReporteResponse;
import com.miproyecto.trueque.service.ReporteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reporte")
@RequiredArgsConstructor
public class ReporteController {
    private final ReporteService reporteService;

    @PostMapping("/ver")
    public ResponseEntity<?> generarReporte(@RequestBody ReporteRequest request) {
        try {
            List<ReporteResponse> reporte = reporteService.generarReporte(request);


            return ResponseEntity.ok(reporte);

        } catch (IllegalArgumentException e) {
            log.warn("Error de validación en el reporte: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solicitud inválida: " + e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al generar el reporte", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado al generar el reporte.");
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<?> consultarReportesGuardados(
            @RequestParam Long tipoPeriodoId,
            @RequestParam(required = false) Long periodoId) {
        try {
            ReporteRequest request = new ReporteRequest(tipoPeriodoId, periodoId);
            List<ReporteResponse> reportes = reporteService.obtenerReportesGuardados(request);

            if (reportes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("No se encontraron resultados para los filtros proporcionados.");
            }

            return ResponseEntity.ok(reportes);

        } catch (IllegalArgumentException e) {
            log.warn("Error de validación en la consulta de reportes: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Solicitud inválida: " + e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al consultar los reportes", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado al consultar los reportes.");
        }
    }


}