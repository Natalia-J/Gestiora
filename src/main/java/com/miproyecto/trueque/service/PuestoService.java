package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.PuestoResponse;
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

    public List<PuestoResponse> obtenerTodos() {
        return puestoRepository.findAll()
                .stream()
                .map(p -> new PuestoResponse(p.getId(), p.getNombre()))
                .toList();
    }

    public Optional<Puesto> obtenerPorId(Long id) {
        return puestoRepository.findById(id);
    }

    public void eliminar(Long id) {
        puestoRepository.deleteById(id);
    }
}
