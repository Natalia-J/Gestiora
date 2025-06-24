package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.TipoPrestacionEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoPrestacionRepository extends JpaRepository<TipoPrestacionEmpleado, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(ti.id, CAST(ti.tipoPrestacionEmpleado as string)) FROM TipoPrestacionEmpleado ti")
    List<GenericCatalogResponse> getAllTipoPrestacion();
}
