package com.miproyecto.trueque.interceptor;

import com.miproyecto.trueque.config.PublicRoutes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class EmpresaInterceptor implements HandlerInterceptor {
    private static final String EMPRESA_HEADER = "X-Empresa-Id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getRequestURI();

        boolean esRutaPublica = PublicRoutes.obtenerRutas().stream()
                .anyMatch(path::startsWith);

        boolean esRutaSinEmpresa = PublicRoutes.rutasSinEmpresa().stream()
                .anyMatch(path::startsWith);

        if (esRutaPublica || esRutaSinEmpresa) {
            return true;
        }

        String empresaIdHeader = request.getHeader(EMPRESA_HEADER);
        if (empresaIdHeader == null) {
            throw new IllegalArgumentException("El encabezado 'X-Empresa-Id' es obligatorio.");
        }

        try {
            Long empresaId = Long.parseLong(empresaIdHeader);
            EmpresaContextHolder.setEmpresaId(empresaId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El encabezado 'X-Empresa-Id' debe ser un número válido.");
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        EmpresaContextHolder.clear();
    }
}
