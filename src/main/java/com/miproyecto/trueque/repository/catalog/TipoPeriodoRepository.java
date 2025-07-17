package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.catalogs.TipoPeriodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoPeriodoRepository extends JpaRepository<TipoPeriodo, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(ti.id, CAST(ti.periodo as string)) FROM TipoPeriodo ti")
    List<GenericCatalogResponse> getAllTipoPeriodo();
    List<TipoPeriodo> findByEmpresaOrderByFechaInicioPeriodoDesc(Company empresa);
    Optional<TipoPeriodo> findTopByEmpresaAndCerradoFalse(Company empresa);
}
