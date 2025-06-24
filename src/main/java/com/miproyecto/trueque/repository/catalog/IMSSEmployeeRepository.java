package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.AvisoIMSSEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMSSEmployeeRepository extends JpaRepository<AvisoIMSSEmpleado, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(imss.id, CAST(imss.avisoImssEmpleado as string))From AvisoIMSSEmpleado imss")
    List<GenericCatalogResponse> getAllAvisoIMSS();
}
