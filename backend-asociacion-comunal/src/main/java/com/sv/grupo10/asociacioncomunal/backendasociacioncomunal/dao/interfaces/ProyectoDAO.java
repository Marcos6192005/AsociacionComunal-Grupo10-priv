package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Proyecto;

import java.util.List;
import java.util.Optional;

public interface ProyectoDAO {
    Proyecto guardarProyecto(Proyecto proyecto);
    List<Proyecto> listarProyectos();
    Optional<Proyecto> buscarPorId(String id);
    Optional<Proyecto> actualizarProyecto(String id, Proyecto proyecto);
    boolean eliminarProyecto(String id);
}
