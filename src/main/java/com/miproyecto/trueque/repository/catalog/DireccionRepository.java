package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.model.catalogs.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
}
