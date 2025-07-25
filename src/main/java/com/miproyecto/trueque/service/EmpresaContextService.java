package com.miproyecto.trueque.service;

import com.miproyecto.trueque.interceptor.EmpresaContextHolder;
import com.miproyecto.trueque.model.Company;
import com.miproyecto.trueque.repository.EmpresaRepository;
import org.springframework.stereotype.Component;

@Component
public class EmpresaContextService {
    private final EmpresaRepository empresaRepository;

    public EmpresaContextService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Company getEmpresaActual() {
        Long empresaId = EmpresaContextHolder.getEmpresaId();
        if (empresaId == null) {
            throw new IllegalStateException("No se ha encontrado ID de empresa en el contexto");
        }
        return empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalStateException("Empresa no encontrada con ID: " + empresaId));
    }
}
