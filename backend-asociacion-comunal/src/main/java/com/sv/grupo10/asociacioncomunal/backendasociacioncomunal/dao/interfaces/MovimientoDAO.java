package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Movimiento;

import java.util.List;

public interface MovimientoDAO {
    Movimiento guardar(Movimiento movimiento);
    List<Movimiento> listar();
}
