package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Comunicado;

import java.util.List;
import java.util.Optional;

public interface ComunicadoDAO {
    Comunicado guardar(Comunicado comunicado);
    List<Comunicado> listar();
    Optional<Comunicado> buscarPorId(String id);
}
