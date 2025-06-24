package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.RegimenEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegimenEmpleadoRepository extends JpaRepository<RegimenEmployee, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(re.id, CAST(re.regimenFiscalEmployee as string)) FROM RegimenEmployee re")
    List<GenericCatalogResponse> getAllRegimenEmpleado();
}
