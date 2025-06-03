package com.miproyecto.trueque.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UsuarioYaRegistradoException extends RuntimeException{
    private HttpStatus httpStatus;
    private HttpStatus code;

    public UsuarioYaRegistradoException(String mensaje){
        super(mensaje);
    }
}
