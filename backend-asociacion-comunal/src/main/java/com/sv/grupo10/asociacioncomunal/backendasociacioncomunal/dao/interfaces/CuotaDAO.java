package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Cuota;

import java.util.List;
import java.util.Optional;

public interface CuotaDAO {
    Cuota guardar(Cuota cuota);
    List<Cuota> listar();
    Optional<Cuota> buscarPorId(String id);
    Optional<Cuota> actualizar(String id, Cuota cuota);
}
