/*package com.miproyecto.trueque.model.catalogs;

import com.miproyecto.trueque.model.Departamento;
import com.miproyecto.trueque.repository.DepartamentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final DepartamentoRepository departamentoRepository;

    public DataLoader(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!departamentoRepository.existsByNombreDepartamento("General")) {
            Departamento general = new Departamento();
            general.setNombreDepartamento("General");
            departamentoRepository.save(general);
        }
    }
}*/
