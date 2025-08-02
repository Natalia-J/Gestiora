package com.miproyecto.trueque.repository;

import com.miproyecto.trueque.model.Percepciones;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PercepcionesRepository extends JpaRepository<Percepciones, Integer> {
    Optional<Percepciones> findByCodigoEmpleadoAndPeriodoPago(String codigoEmpleado, PeriodoPago periodoPago);


}
