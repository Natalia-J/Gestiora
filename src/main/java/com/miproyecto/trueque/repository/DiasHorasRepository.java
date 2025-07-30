package com.miproyecto.trueque.repository;

import com.miproyecto.trueque.model.DiasHoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiasHorasRepository extends JpaRepository<DiasHoras, Long> {
    List<DiasHoras> findByEmpleadoId(Long empleadoId);
}
