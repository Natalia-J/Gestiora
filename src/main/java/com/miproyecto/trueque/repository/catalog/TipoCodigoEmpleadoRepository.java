package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.TipoCodigoEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoCodigoEmpleadoRepository extends JpaRepository<TipoCodigoEmpleado, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(ti.id, CAST(ti.codigo as string)) FROM TipoCodigoEmpleado ti")
    List<GenericCatalogResponse> getAllTipoCodigo();
}
