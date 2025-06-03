package com.miproyecto.trueque.controller;

import com.miproyecto.trueque.dto.LoginRequest;
import com.miproyecto.trueque.dto.LoginResponse;
import com.miproyecto.trueque.dto.RegisterRequest;
import com.miproyecto.trueque.model.Client;
import com.miproyecto.trueque.service.AuthenticationService;
import com.miproyecto.trueque.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
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
    public ResponseEntity<?> registerClient(@RequestBody RegisterRequest register){
        return ResponseEntity.ok(Map.of("message",authenticationService.registerClient(register)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Client client = authenticationService.login(loginRequest.getEmail(), loginRequest.getPassword());

        String token = jwtService.generateToken(client);
        LoginResponse response = new LoginResponse(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/mensaje")
    public ResponseEntity<?> mensaje(){
        return ResponseEntity.ok("mensaje");
    }

}

