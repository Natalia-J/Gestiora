package com.miproyecto.trueque.config;

import java.util.List;

public abstract class PublicRoutes {
    public static List<String> obtenerRutas() {
        return List.of(
                "/api/clients/register",
                "/api/clients/login",
                "/api/prenomina/export/pdf/**"
        );
    }
    public static List<String> rutasSinEmpresa() {
        return List.of(
                "/api/empresa/crear",
                "/api/empresa/obtener"
        );
    }
}
