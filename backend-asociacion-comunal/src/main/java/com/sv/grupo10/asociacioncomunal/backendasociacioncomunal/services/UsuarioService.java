package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Usuario;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> autenticar(String correo, String password);
}