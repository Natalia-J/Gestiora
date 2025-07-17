package com.miproyecto.trueque.service;

import com.miproyecto.trueque.model.Puesto;
import com.miproyecto.trueque.repository.PuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PuestoService {

    private final PuestoRepository puestoRepository;

    public PuestoService(PuestoRepository puestoRepository) {
        this.puestoRepository = puestoRepository;
    }

    public Puesto guardar(Puesto puesto) {
        return puestoRepository.save(puesto);
    }

    public List<Puesto> obtenerTodos() {
        return puestoRepository.findAll();
    }

    public Optional<Puesto> obtenerPorId(Long id) {
        return puestoRepository.findById(id);
    }

    public void eliminar(Long id) {
        puestoRepository.deleteById(id);
    }
}
