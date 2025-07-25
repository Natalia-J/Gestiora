package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.TipoPeriodoEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoPeriodoEmpleadoRepository extends JpaRepository<TipoPeriodoEmpleado, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(ti.id, CAST(ti.tipoPeriodoEmpleado AS string)) FROM TipoPeriodoEmpleado ti")
    List<GenericCatalogResponse> getAllTipoPeriodoEmpleado();

}
