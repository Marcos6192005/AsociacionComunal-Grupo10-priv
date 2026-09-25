package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ActaRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ComunicadoRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ResponderSolicitudRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.SolicitudRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Acta;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Comunicado;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Solicitud;

import java.util.List;

public interface SecretariaService {
    List<Solicitud> listarSolicitudes();
    List<Solicitud> listarSolicitudesPorAutor(String correoAutor);
    Solicitud crearSolicitud(String correoAutor, SolicitudRequestDTO request);
    Solicitud responderSolicitud(String correoAdmin, String solicitudId, ResponderSolicitudRequestDTO request);

    List<Acta> listarActasAdmin(String correoAdmin);
    List<Acta> listarActasPublicadas();
    Acta crearActa(String correoAdmin, ActaRequestDTO request);
    Acta publicarActa(String correoAdmin, String actaId);

    List<Comunicado> listarComunicados();
    Comunicado crearComunicado(String correoAdmin, ComunicadoRequestDTO request);
}
