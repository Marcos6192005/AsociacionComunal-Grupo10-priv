package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos;

import java.util.List;

public record ActaRequestDTO(
        String titulo,
        String contenido,
        String fecha,
        List<AcuerdoActaDTO> acuerdos
) {}
