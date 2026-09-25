package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.impl;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces.ProyectoDAO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ProyectoDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Proyecto;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.ProyectoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoDAO proyectoDAO;

    public ProyectoServiceImpl(ProyectoDAO proyectoDAO) {
        this.proyectoDAO = proyectoDAO;
    }

    @Override
    public Proyecto crearProyecto(ProyectoDTO proyectoDTO) {
        Proyecto proyecto = new Proyecto(
                proyectoDTO.nombre(),
                proyectoDTO.descripcion(),
                proyectoDTO.estado(),
                proyectoDTO.fechaInicio()
        );

        return proyectoDAO.guardarProyecto(proyecto);
    }

    @Override
    public List<Proyecto> listarProyectos() {
        return proyectoDAO.listarProyectos();
    }

    @Override
    public Optional<Proyecto> obtenerPorId(String id) {
        return proyectoDAO.buscarPorId(id);
    }

    @Override
    public Optional<Proyecto> actualizarProyecto(String id, ProyectoDTO proyectoDTO) {
        Proyecto proyecto = new Proyecto(
                proyectoDTO.nombre(),
                proyectoDTO.descripcion(),
                proyectoDTO.estado(),
                proyectoDTO.fechaInicio()
        );

        return proyectoDAO.actualizarProyecto(id, proyecto);
    }

    @Override
    public boolean eliminarProyecto(String id) {
        return proyectoDAO.eliminarProyecto(id);
    }
}
