package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> autenticar(String correo, String password);
    Optional<Usuario> buscarPorCorreo(String correo);
    Usuario crearUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    Optional<Usuario> buscarPorId(String id);
    Usuario actualizarUsuario(Usuario usuario);
    void eliminarUsuario(String id);
}