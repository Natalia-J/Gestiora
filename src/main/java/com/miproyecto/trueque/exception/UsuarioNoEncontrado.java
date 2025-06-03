package com.miproyecto.trueque.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UsuarioNoEncontrado extends RuntimeException {

    public UsuarioNoEncontrado(String mensaje){
        super(mensaje);
    }
}
