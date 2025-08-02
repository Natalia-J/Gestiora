package com.miproyecto.trueque.repository;

import com.miproyecto.trueque.model.Employee;
import com.miproyecto.trueque.model.PeriodoEmpleado;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import com.miproyecto.trueque.model.catalogs.TipoPeriodoEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodoEmpleadoRepository extends JpaRepository<PeriodoEmpleado, Long> {
    List<PeriodoEmpleado> findByEmpleadoAndActivoTrue(Employee empleado);
    boolean existsByEmpleadoAndPeriodoPago(Employee empleado, PeriodoPago periodoPago);
    List<PeriodoEmpleado> findByEmpleado(Employee empleado);
    boolean existsByEmpleadoAndPeriodoPago(Employee empleado, TipoPeriodoEmpleado periodoPago);
}

