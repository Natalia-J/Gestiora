package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.MetodoPagoEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPagoEmpleado, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(me.id, CAST(me.metodoPago as string)) FROM MetodoPagoEmpleado me")
    List<GenericCatalogResponse> getAllMetodoPago();
}
