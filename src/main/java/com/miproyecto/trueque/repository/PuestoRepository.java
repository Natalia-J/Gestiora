package com.miproyecto.trueque.repository;

import com.miproyecto.trueque.model.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuestoRepository extends JpaRepository<Puesto, Long> {
    boolean existsByNombre(String nombre);
}
