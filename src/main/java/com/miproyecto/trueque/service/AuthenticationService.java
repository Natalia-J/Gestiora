package com.miproyecto.trueque.service;

import com.miproyecto.trueque.dto.RegisterRequest;
import com.miproyecto.trueque.exception.CorreoYaRegistradoException;
import com.miproyecto.trueque.exception.PasswordInvalidException;
import com.miproyecto.trueque.exception.UsuarioNoEncontrado;
import com.miproyecto.trueque.exception.UsuarioYaRegistradoException;
import com.miproyecto.trueque.model.Client;
import com.miproyecto.trueque.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerClient(RegisterRequest register) {
        if(clientRepository.findByEmail(register.getEmail()).isPresent()) {
            throw new CorreoYaRegistradoException("EL correo ya esta registrado", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }

        if(clientRepository.findByUsername(register.getUsername()).isPresent()) {
            throw new UsuarioYaRegistradoException("El usuario se encuentra en uso");
        }

        Client newClient = new Client();
        newClient.setUsername(register.getUsername());
        newClient.setPassword(passwordEncoder.encode(register.getPassword()));
        newClient.setEmail(register.getEmail());
        clientRepository.save(newClient);
        return"El registro ha sido exitoso";

    }

    public Client obtenerPorCorreo(String email){
        return clientRepository.findByEmail(email)
                .orElseThrow(()-> new UsuarioNoEncontrado("No existe una cuenta con ese correo"));
    }

    public Client login(String email, String password){
        Client client = obtenerPorCorreo(email);

        if(!passwordEncoder.matches(password, client.getPassword())){
            throw new PasswordInvalidException("La contraseña es incorrecta");
        }
        return client;
    }

}
