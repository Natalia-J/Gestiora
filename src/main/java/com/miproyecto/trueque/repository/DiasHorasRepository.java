package com.miproyecto.trueque.repository;

import com.miproyecto.trueque.model.DiasHoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiasHorasRepository extends JpaRepository<DiasHoras, Long> {
    List<DiasHoras> findByEmpleadoIdAndFechaBetween(Long empleadoId, LocalDate inicio, LocalDate fin);
    List<DiasHoras> findByEmpleadoId(Long empleadoId);
    List<DiasHoras> findByTipoPeriodoId(Long tipoPeriodoId);
    boolean existsByFechaAndEmpleadoId(LocalDate fecha, Long empleadoId);
}
