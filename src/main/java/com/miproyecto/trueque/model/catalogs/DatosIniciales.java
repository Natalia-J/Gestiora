package com.miproyecto.trueque.model.catalogs;


import com.miproyecto.trueque.model.Departamento;
import com.miproyecto.trueque.repository.DepartamentoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales {
    private final DepartamentoRepository departamentoRepository;

    public DatosIniciales(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

   /* @PostConstruct
    public void init() {
        if (departamentoRepository.findByCodigo("GEN").isEmpty()) {
            Departamento general = new Departamento();
            general.setCodigo("GEN");
            general.setNombreDepartamento("General");
            departamentoRepository.save(general);
        }
    }*/
}
