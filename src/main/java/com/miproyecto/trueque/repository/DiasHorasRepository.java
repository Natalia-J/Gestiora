package com.miproyecto.trueque.repository;

import com.miproyecto.trueque.model.DiasHoras;
import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiasHorasRepository extends JpaRepository<DiasHoras, Long> {
    List<DiasHoras> findByEmpleado_Id(Long empleadoId);
    boolean existsByEmpleadoAndPeriodoActivoAndFecha(Employee empleado, PeriodoPago periodopago, LocalDate fecha);
    Optional<DiasHoras> findByEmpleadoAndPeriodoActivoAndFecha(Employee empleado, PeriodoPago periodoActivo, LocalDate fecha);
    Optional<DiasHoras> findByEmpleado_IdAndPeriodoActivo_IdAndFecha(Long empleadoId, Long periodoId, LocalDate fecha);
    List<DiasHoras> findAllByEmpleadoIdAndFechaBetween(Long empleadoId, LocalDate fechaInicio, LocalDate fechaFin);
    List<DiasHoras> findByEmpleado_IdAndPeriodoActivo_Id(Long empleadoId, Long periodoActivoId);

}
