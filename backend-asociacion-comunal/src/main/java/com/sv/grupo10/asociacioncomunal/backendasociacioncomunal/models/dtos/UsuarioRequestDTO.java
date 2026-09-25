package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos;

public record UsuarioRequestDTO(
        String tipo,        // "VECINO" o "DIRECTIVA"
        String nombre,
        String correo,
        String password,
        String numeroCasa,  // solo aplica si tipo = VECINO
        String cargo        // solo aplica si tipo = DIRECTIVA
) {}
