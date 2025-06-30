package com.miproyecto.trueque.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "prenomina")
public class Prenomina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
