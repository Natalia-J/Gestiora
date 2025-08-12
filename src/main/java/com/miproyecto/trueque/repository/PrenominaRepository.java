package com.miproyecto.trueque.repository;

import com.miproyecto.trueque.dto.PrenominaResponse;
import com.miproyecto.trueque.dto.PrenominaResponseTabla;
import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.model.Prenomina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrenominaRepository extends JpaRepository<Prenomina, Long> {
    List<Prenomina> findByPeriodoPago_Id(Long periodoPagoId);
    Optional<Prenomina> findByEmpleado_Id(Long empleadoId);

    @Query("SELECT p FROM Prenomina p WHERE p.empleado.id = :empleadoId")
    Optional<Prenomina> findByEmpleadoId(@Param("empleadoId") Long empleadoId);
    Optional<Prenomina> findByEmpleado_IdAndPeriodoPago_Id(Long empleadoId, Long periodoPagoId);

    @Query("""
    SELECT new com.miproyecto.trueque.dto.PrenominaResponseTabla(
        e.id,
        e.codigoEmpleado,
        CONCAT(e.nombreEmpleado, ' ', e.apellidoPaternoEmpleado, ' ', e.apellidoMaternoEmpleado),
        p.sueldoBase,
        p.horasExtras,
        p.bono,
        p.comisiones,
        p.gratificaciones,
        p.aguinaldoProporcional,
        p.PrimaVacacional,
        p.ISR,
        p.IMSS,
        p.INFONAVIT,
        p.otrasDeducciones,
        p.totalNeto
    )
    FROM Prenomina p
    JOIN p.empleado e
    WHERE e.tipoPeriodo.id = :tipoPeriodoId
""")
    List<PrenominaResponseTabla> findByTipoPeriodoEmpleado(@Param("tipoPeriodoId") Long tipoPeriodoId);


}

