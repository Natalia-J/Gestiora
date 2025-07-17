package com.miproyecto.trueque.interceptor;

public class EmpresaContextHolder {
    private static final ThreadLocal<Long> empresaIdHolder = new ThreadLocal<>();

    public static void setEmpresaId(Long empresaId) {
        empresaIdHolder.set(empresaId);
    }

    public static Long getEmpresaId() {
        return empresaIdHolder.get();
    }

    public static void clear() {
        empresaIdHolder.remove();
    }
}
