package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos;

public record CuotaRequestDTO(
        String correoVecino,
        String periodo,
        Double monto
) {}
