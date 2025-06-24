package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.ZonaSalarioGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZonaSalarioRepository extends JpaRepository<ZonaSalarioGeneral, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(zo.id, CAST(zo.zonaSalario as string)) FROM ZonaSalarioGeneral zo")
    List<GenericCatalogResponse> getAllZonaSalario();
}
