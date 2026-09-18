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

            // 3. Rol Vecino
            Vecino vecino = new Vecino(
                    "Vecino 1",
                    "vecino@adesco.com",
                    "vecino123",
                    "Casa 42, Senda B"
            );

            guardarUsuario(admin);
            guardarUsuario(tesorero);
            guardarUsuario(vecino);
            System.out.println("Usuario de prueba creado: admin@adesco.com / admin123");
        }
    }
}
