package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Usuario;

import java.util.Optional;

public interface UsuarioDAO {
    void guardarUsuario(Usuario usuario);
    Optional<Usuario> buscarPorCorreo(String correo);
}
