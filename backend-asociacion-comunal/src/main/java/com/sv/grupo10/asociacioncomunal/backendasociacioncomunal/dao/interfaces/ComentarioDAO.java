package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Comentario;

import java.util.List;

public interface ComentarioDAO {
    List<Comentario> listarComentarios();
    List<Comentario> listarPorProyecto(String proyectoId);
    Comentario guardarComentario(Comentario comentario);
}
