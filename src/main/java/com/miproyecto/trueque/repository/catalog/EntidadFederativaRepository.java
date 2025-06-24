package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.EntidadFederativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntidadFederativaRepository extends JpaRepository<EntidadFederativa, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(en.id, CAST(en.entidadFederativa as string)) FROM EntidadFederativa en")
    List<GenericCatalogResponse> getAllEntidad();
}
