package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.EstadoCivilEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivilEmpleado, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(es.id, CAST(es.estadoCivilEmpleado as string)) FROM EstadoCivilEmpleado es")
    List<GenericCatalogResponse> getAllEstadoCivil();
}
