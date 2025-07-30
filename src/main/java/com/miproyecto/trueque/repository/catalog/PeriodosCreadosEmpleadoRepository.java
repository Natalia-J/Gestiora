package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodosCreadosEmpleadoRepository extends JpaRepository<PeriodoPago, Long> {
    List<PeriodoPago> findByEmpresaIdAndTipoPeriodoEmpleadoId(Long empresaId, Long tipoPeriodoEmpleadoId);
    Optional<PeriodoPago> findFirstByEmpresaIdAndTipoPeriodoEmpleadoIdOrderByFechaFinDesc(Long empresaId, Long tipoPeriodoId);
    Optional<PeriodoPago> findByEmpresa_IdAndEstadoTrue(Long empresaId);

}
