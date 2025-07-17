package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.BaseDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasePagoRepository extends JpaRepository<BaseDePago, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(ba.id, CAST(ba.baseDePago as string)) FROM BaseDePago ba")
    List<GenericCatalogResponse> getAllBasePago();
}
