package com.miproyecto.trueque.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "departamento", uniqueConstraints = @UniqueConstraint(columnNames = {"empresa_id", "codigo"}))
public class Departamento {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String codigo;

        @Column(name = "nombre", nullable = false)
        private String nombreDepartamento;

        @OneToMany(mappedBy = "departamentoEmple", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference
        private List<Employee> empleados;

        @ManyToOne(optional = false)
        @JoinColumn(name = "empresa_id", nullable = false)
        private Company empresa;
}
