package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.TipoContratoEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoContratoEmpleadoRepository extends JpaRepository<TipoContratoEmpleado, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(ti.id, CAST(ti.contrato as string)) FROM TipoContratoEmpleado ti")
    List<GenericCatalogResponse> getAllTipoContrato();
}
