package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.impl;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces.UsuarioDAO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.MiembroDirectiva;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Usuario;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Vecino;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.utils.ArchivoDatUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDAODatImpl implements UsuarioDAO {

    private static final String USUARIOS = "usuarios.dat";

    @Override
    public void guardarUsuario(Usuario usuario) {
        List<Usuario> usuarios = ArchivoDatUtil.leerDatos(USUARIOS);

        usuarios.add(usuario);

        ArchivoDatUtil.guardarDatos(USUARIOS, usuarios);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        List<Usuario> usuarios = ArchivoDatUtil.leerDatos(USUARIOS);

        return usuarios.stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(correo))
                .findFirst();
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return ArchivoDatUtil.leerDatos(USUARIOS);
    }

    @Override
    public Optional<Usuario> buscarPorId(String id) {
        List<Usuario> usuarios = ArchivoDatUtil.leerDatos(USUARIOS);

        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        List<Usuario> usuarios = ArchivoDatUtil.leerDatos(USUARIOS);

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(usuario.getId())) {
                usuarios.set(i, usuario);
                ArchivoDatUtil.guardarDatos(USUARIOS, usuarios);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean eliminarUsuario(String id) {
        List<Usuario> usuarios = ArchivoDatUtil.leerDatos(USUARIOS);

        boolean eliminado = usuarios.removeIf(u -> u.getId().equals(id));

        if (eliminado) {
            ArchivoDatUtil.guardarDatos(USUARIOS, usuarios);
        }

        return eliminado;
    }

    @PostConstruct
    public void poblarDatosIniciales() {
        List<Usuario> usuarios = ArchivoDatUtil.leerDatos(USUARIOS);

        if (usuarios.isEmpty()) {
            System.out.println("No se encontraron usuarios. Creando administrador por defecto...");

            MiembroDirectiva admin = new MiembroDirectiva(
                    "Admin ADESCO",
                    "admin@adesco.com",
                    "admin123",
                    "Presidente"
            );

            MiembroDirectiva tesorero = new MiembroDirectiva(
                    "Tesorera ADESCO",
                    "tesorero@adesco.com",
                    "teso123",
                    "Tesorero"
            );

            MiembroDirectiva secretario = new MiembroDirectiva(
                    "Secretario ADESCO",
                    "secretario@adesco.com",
                    "secre123",
                    "Secretario"
            );

            Vecino vecino = new Vecino(
                    "Vecino 1",
                    "vecino@adesco.com",
                    "vecino123",
                    "Casa 42, Senda B"
            );

            guardarUsuario(admin);
            guardarUsuario(tesorero);
            guardarUsuario(secretario);
            guardarUsuario(vecino);
            System.out.println("Usuario de prueba creado: admin@adesco.com / admin123");
        } else {
            asegurarSecretario(usuarios);
        }
    }

    private void asegurarSecretario(List<Usuario> usuarios) {
        boolean existe = usuarios.stream()
                .anyMatch(usuario -> "secretario@adesco.com".equalsIgnoreCase(usuario.getCorreo()));

        if (!existe) {
            MiembroDirectiva secretario = new MiembroDirectiva(
                    "Secretario ADESCO",
                    "secretario@adesco.com",
                    "secre123",
                    "Secretario"
            );
            guardarUsuario(secretario);
            System.out.println("Secretario de prueba creado: secretario@adesco.com / secre123");
        }
    }
}
