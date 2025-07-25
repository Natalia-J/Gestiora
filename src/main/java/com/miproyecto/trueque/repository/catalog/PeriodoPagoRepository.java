package com.miproyecto.trueque.repository.catalog;

import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.catalogs.PeriodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodoPagoRepository extends JpaRepository<PeriodoPago, Long> {
    @Query("""
    SELECT p FROM PeriodoPago p
    WHERE p.empresa.id = :empresaId
      AND :fecha BETWEEN p.fechaInicio AND p.fechaFin
      AND p.cerrado = false
""")
    Optional<PeriodoPago> findByEmpresaAndFecha(Long empresaId, LocalDate fecha);

    List<PeriodoPago> findByEmpresaOrderByFechaInicioDesc(Company empresa);
    Optional<PeriodoPago> findTopByEmpresaAndCerradoFalseOrderByFechaInicioDesc(Company empresa);

}
