package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Voto;

import java.util.List;
import java.util.Optional;

public interface VotoDAO {
    List<Voto> listarVotos();
    List<Voto> listarPorProyecto(String proyectoId);
    Optional<Voto> buscarPorProyectoYCorreo(String proyectoId, String correo);
    Voto guardarVoto(Voto voto);
}
