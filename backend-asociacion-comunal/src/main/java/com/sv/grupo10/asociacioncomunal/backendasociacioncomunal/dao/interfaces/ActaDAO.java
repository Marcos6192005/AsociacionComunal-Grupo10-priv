package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Acta;

import java.util.List;
import java.util.Optional;

public interface ActaDAO {
    Acta guardar(Acta acta);
    List<Acta> listar();
    Optional<Acta> buscarPorId(String id);
    Optional<Acta> actualizar(String id, Acta acta);
}
