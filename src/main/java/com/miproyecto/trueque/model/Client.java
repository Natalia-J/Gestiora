package com.miproyecto.trueque.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(nullable = false, name = "password")
    private String password;
    @Column(name="email", nullable = false, unique = true)
    private String email;
}
