package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.MiembroDirectiva;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Usuario;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Vecino;

public record UsuarioResponseDTO(
        String id,
        String nombre,
        String correo,
        String rol,
        String numeroCasa,
        String cargo
) {
    public static UsuarioResponseDTO desde(Usuario usuario) {
        String numeroCasa = null;
        String cargo = null;

        if (usuario instanceof Vecino vecino) {
            numeroCasa = vecino.getNumeroCasa();
        } else if (usuario instanceof MiembroDirectiva directiva) {
            cargo = directiva.getCargo();
        }

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getRol(),
                numeroCasa,
                cargo
        );
    }
}
