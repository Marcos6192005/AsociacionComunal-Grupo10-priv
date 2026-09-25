package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.security;

import java.util.Locale;
import java.util.Set;

public final class Roles {

    public static final String VECINO = "VECINO";
    public static final String ADMINISTRACION = "ADMINISTRACION";
    public static final String PRESIDENTE = "PRESIDENTE";
    public static final String SECRETARIO = "SECRETARIO";
    public static final String TESORERO = "TESORERO";

    private static final Set<String> ROLES_DIRECTIVA = Set.of(
            ADMINISTRACION,
            PRESIDENTE,
            SECRETARIO,
            TESORERO
    );

    private Roles() {
    }

    public static String normalizar(String valor) {
        if (valor == null || valor.isBlank()) {
            return "";
        }
        return valor.trim().toUpperCase(Locale.ROOT);
    }

    public static String rolDesdeCargo(String cargo) {
        String normalizado = normalizar(cargo);
        if (normalizado.isEmpty()) {
            return ADMINISTRACION;
        }
        if (ROLES_DIRECTIVA.contains(normalizado) || VECINO.equals(normalizado)) {
            return normalizado;
        }
        return ADMINISTRACION;
    }

    public static boolean esRolDirectiva(String rol) {
        return ROLES_DIRECTIVA.contains(normalizar(rol));
    }
}
