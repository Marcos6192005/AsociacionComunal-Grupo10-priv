package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.BalanceDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.CuotaRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.MovimientoRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Cuota;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Movimiento;

import java.util.List;

public interface TesoreriaService {
    List<Movimiento> listarMovimientos(String correoAdmin);
    Movimiento registrarMovimiento(String correoAdmin, MovimientoRequestDTO request);
    BalanceDTO calcularBalance();

    List<Cuota> listarCuotas(String correoAdmin);
    List<Cuota> listarCuotasPorVecino(String correoVecino);
    Cuota crearCuota(String correoAdmin, CuotaRequestDTO request);
    Cuota marcarCuotaPagada(String correoAdmin, String cuotaId);
}
