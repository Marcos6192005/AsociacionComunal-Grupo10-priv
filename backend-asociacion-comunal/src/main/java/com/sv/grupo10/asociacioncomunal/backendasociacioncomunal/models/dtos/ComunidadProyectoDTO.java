package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos;

public record ComunidadProyectoDTO(
        String id,
        String nombre,
        String descripcion,
        String estado,
        String fechaInicio,
        int votosAFavor,
        int votosEnContra,
        boolean yaVoto,
        String votoUsuario,
        int totalComentarios
) {}
