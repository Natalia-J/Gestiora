package com.miproyecto.trueque.config;

import java.util.List;

public abstract class PublicRoutes {
    public static List<String> obtenerRutas() {
        return List.of(
                "api/clients/register",
                "api/clients/login"
        );
    }
}
