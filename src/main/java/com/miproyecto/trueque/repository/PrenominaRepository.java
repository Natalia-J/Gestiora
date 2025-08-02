package com.miproyecto.trueque.repository;

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



}
