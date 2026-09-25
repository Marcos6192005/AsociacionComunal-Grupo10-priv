package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos;

public record ComentarioResponseDTO(
        String id,
        String proyectoId,
        String nombreProyecto,
        String autor,
        String texto,
        String fecha
) {}
