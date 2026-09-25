package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.RegistroEmail;

import java.util.List;
import java.util.Optional;

public interface EmailLogDAO {
    void guardar(RegistroEmail registro);
    void actualizar(RegistroEmail registro);
    Optional<RegistroEmail> buscarPorId(String id);
    List<RegistroEmail> listarTodos();
}
