package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaSemanaRepository extends JpaRepository<DiaSemana, Long> {
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(di.id, CAST(di.diaSemana as string)) FROM DiaSemana di")
    List<GenericCatalogResponse> getAllDiaSemana();
    List<DiaSemana> findAllById(Iterable<Long> ids);

}
