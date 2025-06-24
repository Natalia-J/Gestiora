package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.RegimenFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegimenEmpresaRepository extends JpaRepository<RegimenFiscal, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(re.id, CAST(re.regimenEmpresa as string)) FROM RegimenFiscal re")
    List<GenericCatalogResponse> getAllRegimenEmpresa();
}
