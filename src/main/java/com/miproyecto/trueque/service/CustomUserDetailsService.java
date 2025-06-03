package com.miproyecto.trueque.service;

import com.miproyecto.trueque.exception.UsuarioNoEncontrado;
import com.miproyecto.trueque.jwt.CustomUserDetails;
import com.miproyecto.trueque.model.Client;
import com.miproyecto.trueque.repository.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    public CustomUserDetailsService(ClientRepository clientRespository){
        this.clientRepository=clientRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsername(username)
                .orElseThrow(() -> new UsuarioNoEncontrado("Usuario no encontrado."));
        return new CustomUserDetails(client);
    }
}
