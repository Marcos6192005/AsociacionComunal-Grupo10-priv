package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ProyectoDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Proyecto;

import java.util.List;
import java.util.Optional;

public interface ProyectoService {
    Proyecto crearProyecto(ProyectoDTO proyectoDTO);
    List<Proyecto> listarProyectos();
    Optional<Proyecto> obtenerPorId(String id);
    Optional<Proyecto> actualizarProyecto(String id, ProyectoDTO proyectoDTO);
    boolean eliminarProyecto(String id);
}
