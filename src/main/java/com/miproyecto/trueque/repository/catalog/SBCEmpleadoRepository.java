package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.BaseCotizacionEmpleado;
import com.miproyecto.trueque.model.catalogs.SBCEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SBCEmpleadoRepository extends JpaRepository<SBCEmpleado,Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(sbc.id, CAST(sbc.sbc as string)) FROM SBCEmpleado sbc")
    List<GenericCatalogResponse> getAllSBCEmpl();
}
