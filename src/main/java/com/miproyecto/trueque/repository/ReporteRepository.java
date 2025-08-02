package com.miproyecto.trueque.repository;

import com.miproyecto.trueque.model.Reporte;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    boolean existsByPeriodoPago_Id(Long periodoId);
    boolean existsByPeriodoPagoAndCodigoEmpleado(PeriodoPago periodoPago, String codigoEmpleado);
    List<Reporte> findByPeriodoPago_Id(Long periodoId);
    List<Reporte> findByPeriodoPago_TipoPeriodoEmpleado_Id(Long tipoPeriodoEmpleadoId);
    List<Reporte> findByPeriodoPago_TipoPeriodoEmpleado_IdAndPeriodoPago_Id(Long tipoPeriodoEmpleadoId, Long periodoPagoId);
    Optional<Reporte> findByCodigoEmpleadoAndPeriodoPago_Id(String codigoEmpleado, Long periodoPagoId);

}
