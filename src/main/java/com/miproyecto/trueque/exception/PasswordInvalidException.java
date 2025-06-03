package com.miproyecto.trueque.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PasswordInvalidException extends RuntimeException{
    private HttpStatus httpStatus;
    private HttpStatus code;
    public PasswordInvalidException(String mensaje){
        super(mensaje);
    }
}
