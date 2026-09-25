package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos;

public record MovimientoRequestDTO(
        String tipo,
        String concepto,
        Double monto,
        String fecha,
        String proyectoId
) {}
