package com.miproyecto.trueque.repository;

import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodoPagoRepository extends JpaRepository<PeriodoPago, Long> {
    List<PeriodoPago> findByTipoPeriodoEmpleado_Id(Long tipoPeriodoEmpleadoId);
}
