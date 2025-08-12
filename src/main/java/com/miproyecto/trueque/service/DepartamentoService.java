package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.DepartamentoRequest;
import com.miproyecto.trueque.interceptor.EmpresaContextHolder;
import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.model.Departamento;
import com.miproyecto.trueque.repository.DepartamentoRepository;
import com.miproyecto.trueque.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final EmployeeRepository employeeRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository, EmployeeRepository employeeRepository){
        this.departamentoRepository=departamentoRepository;
        this.employeeRepository=employeeRepository;
    }

    // Servicio
    public Departamento guardar(DepartamentoRequest dto) {
        Departamento depto = new Departamento();
        depto.setCodigo(dto.getCodigo());
        depto.setNombreDepartamento(dto.getNombreDepartamento());

        if (departamentoRepository.findByCodigo(depto.getCodigo()).isPresent()) {
            throw new RuntimeException("Ya existe un departamento con ese código");
        }

        if (depto.getEmpresa() == null) {
            Long empresaId = EmpresaContextHolder.getEmpresaId();
            Company empresa = new Company();
            empresa.setId(empresaId);
            depto.setEmpresa(empresa);
        }

        return departamentoRepository.save(depto);
    }


    public boolean existeDepartamentoGenParaEmpresa(Company empresa) {
        return departamentoRepository.findByCodigoAndEmpresa("GEN", empresa).isPresent();
    }


    public List<Departamento> listar() {
        Long empresaId = EmpresaContextHolder.getEmpresaId();
        return departamentoRepository.findByEmpresaId(empresaId);
    }

    public Departamento buscarPorCodigo(String codigo) {
        return departamentoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Código no encontrado"));
    }

    public boolean existenEmpleadosEnDepartamento(Long departamentoId) {
        return employeeRepository.existsByDepartamentoEmple_Id(departamentoId);
    }


    public void eliminar(Long id) {
        Long empresaId = EmpresaContextHolder.getEmpresaId();

        Departamento depto = departamentoRepository.findById(id)
                .filter(d -> d.getEmpresa().getId().equals(empresaId))
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado o no pertenece a la empresa"));

        if ("GEN".equalsIgnoreCase(depto.getCodigo())) {
            throw new IllegalStateException("No se puede eliminar el departamento general.");
        }

        if (employeeRepository.existsByDepartamentoEmple_Id(id)) {
            throw new IllegalStateException("El departamento tiene empleados.");
        }

        departamentoRepository.delete(depto);
    }

    @Transactional
    public void eliminarDepartamentoYEmpleados(Long id) {
        Long empresaId = EmpresaContextHolder.getEmpresaId();

        Departamento depto = departamentoRepository.findById(id)
                .filter(d -> d.getEmpresa().getId().equals(empresaId))
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado o no pertenece a la empresa"));

        if ("GEN".equalsIgnoreCase(depto.getCodigo())) {
            throw new IllegalStateException("No se puede eliminar el departamento general.");
        }

        employeeRepository.deleteByDepartamentoEmple_Id(id);
        departamentoRepository.delete(depto);
    }

}
