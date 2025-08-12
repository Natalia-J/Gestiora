package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.LoginRequest;
import com.miproyecto.trueque.dto.LoginResponse;
import com.miproyecto.trueque.dto.RegisterRequest;
import com.miproyecto.trueque.model.Client;
import com.miproyecto.trueque.service.AuthenticationService;
import com.miproyecto.trueque.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/api/clients")
public class AuthenticationController {

    private AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService){
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerClient(@RequestBody RegisterRequest register) {
        Client client = authenticationService.registerClient(register);
        String token = jwtService.generateToken(client);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Client client = authenticationService.login(loginRequest.getEmail(), loginRequest.getPassword());

        String token = jwtService.generateToken(client);
        LoginResponse response = new LoginResponse(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<Client> getMyData(Authentication authentication) {
        Long clientId = Long.parseLong(authentication.getName());
        Client client = authenticationService.obtenerPorId(clientId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(client);
    }

    @PutMapping("/me")
    public ResponseEntity<Client> updateMyData(Authentication authentication, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(null); // o algún mensaje que diga "No permitido actualizar"
    }

}

