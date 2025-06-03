package com.miproyecto.trueque.exception;

import com.miproyecto.trueque.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponse> construccionRespuesta(String mensaje, Object data,  HttpStatus status) {
        ErrorResponse respuesta = new ErrorResponse(mensaje, data, status ,status.value());
        return new ResponseEntity<>(respuesta, status);
    }

    @ExceptionHandler(CorreoYaRegistradoException .class)
    public ResponseEntity<ErrorResponse>correoYaExistente(CorreoYaRegistradoException existente){
        return construccionRespuesta("Correo en uso", existente.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsuarioYaRegistradoException.class)
    public ResponseEntity<ErrorResponse> usuarioYaExistente(UsuarioYaRegistradoException existente){
        return construccionRespuesta("Usuario en uso", existente.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsuarioNoEncontrado.class)
    public ResponseEntity<ErrorResponse> usuarioNoEncontrado(UsuarioNoEncontrado noExistente){
        return construccionRespuesta("Usuario no encontrado", noExistente.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorResponse> passwordInvalid(PasswordInvalidException noValido){
        return construccionRespuesta("Contraseña invalida", noValido.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
