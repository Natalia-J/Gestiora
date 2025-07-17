package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.TipoJornada;
import com.miproyecto.trueque.model.catalogs.TipoPeriodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoJornadaRepository extends JpaRepository<TipoJornada, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(ti.id, CAST(ti.tipoJornada as string)) FROM TipoJornada ti")
    List<GenericCatalogResponse> getAllTipoJornada();
}
