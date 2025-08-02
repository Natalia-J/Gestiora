package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.PercepcionesRequest;
import com.miproyecto.trueque.dto.PercepcionesResponse;
import com.miproyecto.trueque.model.Percepciones;
import com.miproyecto.trueque.model.Reporte;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import com.miproyecto.trueque.repository.PercepcionesRepository;
import com.miproyecto.trueque.repository.ReporteRepository;
import com.miproyecto.trueque.repository.catalog.PeriodosCreadosEmpleadoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PercepcionesService {

    private ReporteRepository reporteRepository;
    private PercepcionesRepository percepcionesRepository;
    private PeriodosCreadosEmpleadoRepository  periodosCreadosEmpleadoRepository;

    public List<PercepcionesResponse> obtenerPercepciones(Long tipoPeriodoEmpleadoId, Long periodoId) {
        // Buscar reportes para ese periodo y tipo
        List<Reporte> reportes = reporteRepository.findByPeriodoPago_TipoPeriodoEmpleado_IdAndPeriodoPago_Id(tipoPeriodoEmpleadoId, periodoId);

        return reportes.stream().map(reporte -> {
            Percepciones percepcion = percepcionesRepository
                    .findByCodigoEmpleadoAndPeriodoPago(reporte.getCodigoEmpleado(), reporte.getPeriodoPago())
                    .orElse(null);

            return new PercepcionesResponse(
                    reporte.getCodigoEmpleado(),
                    reporte.getNombreEmpleado(),
                    reporte.getHorasExtras(),
                    percepcion != null ? percepcion.getBono() : BigDecimal.ZERO,
                    percepcion != null ? percepcion.getComisiones() : BigDecimal.ZERO,
                    percepcion != null ? percepcion.getGratificaciones() : BigDecimal.ZERO,
                    percepcion != null ? percepcion.getAguinaldoProporcional() : BigDecimal.ZERO,
                    percepcion != null ? percepcion.getPrimaVacacional() : BigDecimal.ZERO
            );
        }).collect(Collectors.toList());
    }

    public void guardarPercepciones(String codigoEmpleado, Long periodoId, PercepcionesRequest request) {
        PeriodoPago periodo = periodosCreadosEmpleadoRepository.findById(periodoId)
                .orElseThrow(() -> new RuntimeException("Periodo no encontrado"));

        Percepciones percepcion = percepcionesRepository
                .findByCodigoEmpleadoAndPeriodoPago(codigoEmpleado, periodo)
                .orElse(new Percepciones());

        percepcion.setCodigoEmpleado(codigoEmpleado);
        percepcion.setPeriodoPago(periodo);
        percepcion.setBono(request.getBono());
        percepcion.setComisiones(request.getComisiones());
        percepcion.setGratificaciones(request.getGratificaciones());
        percepcion.setAguinaldoProporcional(request.getAguinaldoProporcional());
        percepcion.setPrimaVacacional(request.getPrimaVacacional());

        percepcionesRepository.save(percepcion);
    }


}
