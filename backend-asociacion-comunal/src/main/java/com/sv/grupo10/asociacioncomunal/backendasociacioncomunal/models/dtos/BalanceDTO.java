package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos;

public record BalanceDTO(
        double totalIngresos,
        double totalEgresos,
        double saldo,
        int cantidadMovimientos
) {}
