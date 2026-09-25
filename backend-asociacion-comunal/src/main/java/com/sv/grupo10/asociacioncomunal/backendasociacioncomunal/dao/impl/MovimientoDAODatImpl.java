package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.impl;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces.MovimientoDAO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Movimiento;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.utils.ArchivoDatUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovimientoDAODatImpl implements MovimientoDAO {

    private static final String ARCHIVO = "movimientos.dat";

    @Override
    public Movimiento guardar(Movimiento movimiento) {
        List<Movimiento> movimientos = ArchivoDatUtil.leerDatos(ARCHIVO);
        movimientos.add(movimiento);
        ArchivoDatUtil.guardarDatos(ARCHIVO, movimientos);
        return movimiento;
    }

    @Override
    public List<Movimiento> listar() {
        return ArchivoDatUtil.leerDatos(ARCHIVO);
    }
}
