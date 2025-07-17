package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.BaseCotizacionEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseCotizacionRepository extends JpaRepository<BaseCotizacionEmpleado, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(ba.id, CAST(ba.baseCotizacion as string)) FROM BaseCotizacionEmpleado ba")
    List<GenericCatalogResponse> getAllBaseCotizacion();
}
