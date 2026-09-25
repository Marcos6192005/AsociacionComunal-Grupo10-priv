package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Solicitud;

import java.util.List;
import java.util.Optional;

public interface SolicitudDAO {
    Solicitud guardar(Solicitud solicitud);
    List<Solicitud> listar();
    Optional<Solicitud> buscarPorId(String id);
    Optional<Solicitud> actualizar(String id, Solicitud solicitud);
}
