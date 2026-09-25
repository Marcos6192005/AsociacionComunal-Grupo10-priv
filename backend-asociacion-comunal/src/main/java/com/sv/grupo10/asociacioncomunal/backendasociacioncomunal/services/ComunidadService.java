package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ComentarioResponseDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ComunidadProyectoDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ParticipacionProyectoDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Comentario;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Voto;

import java.util.List;
import java.util.Optional;

public interface ComunidadService {
    List<ComunidadProyectoDTO> listarProyectos(String correoUsuario);
    List<ParticipacionProyectoDTO> listarParticipacion();
    Optional<ComunidadProyectoDTO> obtenerProyecto(String proyectoId, String correoUsuario);
    Voto votar(String proyectoId, String correoUsuario, String valor);
    List<ComentarioResponseDTO> listarComentarios();
    Comentario comentar(String proyectoId, String correoUsuario, String texto);
}
