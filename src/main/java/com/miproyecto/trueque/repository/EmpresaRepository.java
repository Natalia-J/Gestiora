package com.miproyecto.trueque.repository;

import com.miproyecto.trueque.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Company, Long> {
    boolean existsByRfc(String rfc);
}
