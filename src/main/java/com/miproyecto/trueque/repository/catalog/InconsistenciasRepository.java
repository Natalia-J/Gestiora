package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.Inconsistencias;
import com.miproyecto.trueque.model.enums.InconsistenciasEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InconsistenciasRepository extends JpaRepository<Inconsistencias, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(in.id, CAST(in.inconsistencias as string)) FROM Inconsistencias in")
    List<GenericCatalogResponse> getAllInconsistencias();
    Optional<Inconsistencias> findByInconsistencias(InconsistenciasEnum inconsistencias);
}
