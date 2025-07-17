package com.miproyecto.trueque.repository;

import com.miproyecto.trueque.model.Client;
import com.miproyecto.trueque.model.catalogs.Inconsistencias;
import com.miproyecto.trueque.model.enums.InconsistenciasEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByUsername(String username);

}
