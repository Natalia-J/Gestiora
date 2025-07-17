package com.miproyecto.trueque.repository;

import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Optional<Departamento> findByCodigo(String codigo);
    boolean existsByNombreDepartamento(String nombreDepartamento);
    List<Departamento> findByEmpresaId(Long empresaId);
    Optional<Departamento> findByCodigoAndEmpresa(String codigo, Company empresa);

}
