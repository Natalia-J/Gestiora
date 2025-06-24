package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.dto.GenericCatalogResponse;
import com.miproyecto.trueque.model.catalogs.GeneroEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneroRepository extends JpaRepository<GeneroEmpleado, Long> {

//    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(ge.id, ge.getGenero().name()) FROM GeneroEmpleado ge")
    @Query("SELECT new com.miproyecto.trueque.dto.GenericCatalogResponse(ge.id, CAST(ge.genero as string)) FROM GeneroEmpleado ge")
    List<GenericCatalogResponse> getAllGenres();

}
