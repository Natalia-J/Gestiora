package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import com.miproyecto.trueque.model.catalogs.TipoPeriodoEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodosCreadosEmpleadoRepository extends JpaRepository<PeriodoPago, Long> {
    Optional<PeriodoPago> findByEmpresa_IdAndEstadoTrue(Long empresaId);
    List<PeriodoPago> findAllByEmpresa_IdAndEstadoTrue(Long empresaId);
    Optional<PeriodoPago> findByEmpresa_IdAndEstadoTrueAndTipoPeriodoEmpleadoId(Long empresaId, Long tipoPeriodoEmpleadoId);

    Optional<PeriodoPago> findFirstByEmpresaIdAndEstadoTrueAndTipoPeriodoEmpleadoIdOrderByFechaInicioDesc(
            Long empresaId,
            Long tipoPeriodoEmpleadoId
    );
    //List<PeriodoPago> findByTipoPeriodoEmpleado_Id(Long tipoPeriodoEmpleadoId);
    List<PeriodoPago> findByTipoPeriodoEmpleado_Id(Long tipoPeriodoId);
    Optional<PeriodoPago> findById(Long id);
    Optional<PeriodoPago> findByTipoPeriodoEmpleado_IdAndEstadoTrue(Long tipoPeriodoEmpleadoId);


}
