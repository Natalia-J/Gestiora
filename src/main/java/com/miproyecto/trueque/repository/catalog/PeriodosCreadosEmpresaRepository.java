package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.model.catalogs.PeriodosCreadosEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeriodosCreadosEmpresaRepository extends JpaRepository<PeriodosCreadosEmpresa, Long> {
    Optional<PeriodosCreadosEmpresa> findByEmpresa_IdAndEstadoTrue(Long empresaId);
    Optional<PeriodosCreadosEmpresa> findFirstByEmpresa_IdOrderByFechaInicioDesc(Long empresaId);

}
