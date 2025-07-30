package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.Justificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JustificacionRepository extends JpaRepository<Justificacion, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(ju.id, CAST(ju.justificacion as string)) FROM Justificacion ju")
    List<GenericCatalogResponse> getAllJustificacion();
}
