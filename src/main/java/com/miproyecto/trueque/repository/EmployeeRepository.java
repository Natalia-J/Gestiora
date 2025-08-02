package com.miproyecto.trueque.repository;

import com.miproyecto.trueque.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByCodigoEmpleado(String codigoEmpleado);
    boolean existsByCodigoEmpleado(String codigoEmpleado);
    void deleteByDepartamentoEmple_Id(Long departamentoId);
    boolean existsByDepartamentoEmple_Id(Long departamentoId);
    List<Employee> findByEmpresa_Id(Long empresaId);
    List<Employee> findByTipoPeriodo_Id(Long tipoPeriodoId);


}
