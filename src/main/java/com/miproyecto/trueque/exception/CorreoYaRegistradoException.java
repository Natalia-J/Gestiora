package com.miproyecto.trueque.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class CorreoYaRegistradoException extends RuntimeException{
    private HttpStatus httpStatus;
    private HttpStatusCode code;
    public CorreoYaRegistradoException(String mensaje, HttpStatus status, HttpStatusCode code){
        super(mensaje);
        this.code = code;
        this.httpStatus = status;
    }

}
