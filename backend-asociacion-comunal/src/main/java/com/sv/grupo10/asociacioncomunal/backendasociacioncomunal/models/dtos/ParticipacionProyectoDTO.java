package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos;

public record ParticipacionProyectoDTO(
        String id,
        String nombre,
        String descripcion,
        String estado,
        String fechaInicio,
        int votosAFavor,
        int votosEnContra,
        int totalVotos,
        int totalComentarios
) {}
