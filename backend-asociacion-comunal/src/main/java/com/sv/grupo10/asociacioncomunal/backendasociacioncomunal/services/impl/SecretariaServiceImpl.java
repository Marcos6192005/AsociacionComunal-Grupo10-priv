package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.impl;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces.ActaDAO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces.ComunicadoDAO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces.SolicitudDAO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ActaRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.AcuerdoActaDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ComunicadoRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ResponderSolicitudRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.SolicitudRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Acta;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.AcuerdoActa;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Comunicado;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.MiembroDirectiva;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Solicitud;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Usuario;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.SecretariaService;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.UsuarioService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class SecretariaServiceImpl implements SecretariaService {

    private static final String ESTADO_RECIBIDA = "RECIBIDA";
    private static final String ESTADO_EN_REVISION = "EN_REVISION";
    private static final String ESTADO_RESUELTA = "RESUELTA";
    private static final String ESTADO_RECHAZADA = "RECHAZADA";
    private static final String ESTADO_BORRADOR = "BORRADOR";
    private static final String ESTADO_PUBLICADA = "PUBLICADA";

    private static final Set<String> ESTADOS_RESPUESTA = Set.of(
            ESTADO_EN_REVISION,
            ESTADO_RESUELTA,
            ESTADO_RECHAZADA
    );

    private static final Set<String> CARGOS_ESCRITURA = Set.of("PRESIDENTE", "SECRETARIO");

    private final SolicitudDAO solicitudDAO;
    private final ActaDAO actaDAO;
    private final ComunicadoDAO comunicadoDAO;
    private final UsuarioService usuarioService;

    public SecretariaServiceImpl(
            SolicitudDAO solicitudDAO,
            ActaDAO actaDAO,
            ComunicadoDAO comunicadoDAO,
            UsuarioService usuarioService
    ) {
        this.solicitudDAO = solicitudDAO;
        this.actaDAO = actaDAO;
        this.comunicadoDAO = comunicadoDAO;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<Solicitud> listarSolicitudes() {
        return solicitudDAO.listar();
    }

    @Override
    public List<Solicitud> listarSolicitudesPorAutor(String correoAutor) {
        return solicitudDAO.listar().stream()
                .filter(solicitud -> solicitud.getCorreoAutor().equalsIgnoreCase(correoAutor))
                .toList();
    }

    @Override
    public Solicitud crearSolicitud(String correoAutor, SolicitudRequestDTO request) {
        if (request.titulo() == null || request.titulo().isBlank()) {
            throw new IllegalArgumentException("El titulo es obligatorio");
        }
        if (request.descripcion() == null || request.descripcion().isBlank()) {
            throw new IllegalArgumentException("La descripcion es obligatoria");
        }

        String nombre = usuarioService.buscarPorCorreo(correoAutor)
                .map(Usuario::getNombre)
                .orElse("Vecino");

        Solicitud solicitud = new Solicitud(
                request.titulo().trim(),
                request.descripcion().trim(),
                correoAutor,
                nombre,
                ESTADO_RECIBIDA,
                LocalDate.now().toString()
        );

        return solicitudDAO.guardar(solicitud);
    }

    @Override
    public Solicitud responderSolicitud(String correoAdmin, String solicitudId, ResponderSolicitudRequestDTO request) {
        exigirEscrituraSecretaria(correoAdmin);

        Solicitud existente = solicitudDAO.buscarPorId(solicitudId)
                .orElseThrow(() -> new NoSuchElementException("No existe la solicitud"));

        if (request.estado() == null || request.estado().isBlank()) {
            throw new IllegalArgumentException("Debes indicar el estado de la respuesta");
        }

        String estado = request.estado().trim().toUpperCase();
        if (!ESTADOS_RESPUESTA.contains(estado)) {
            throw new IllegalArgumentException("Estado invalido. Usa EN_REVISION, RESUELTA o RECHAZADA");
        }

        existente.setEstado(estado);
        if (request.respuesta() != null && !request.respuesta().isBlank()) {
            existente.setRespuesta(request.respuesta().trim());
        }
        if (request.proyectoId() != null && !request.proyectoId().isBlank()) {
            existente.setProyectoId(request.proyectoId().trim());
        }

        return solicitudDAO.actualizar(solicitudId, existente)
                .orElseThrow(() -> new NoSuchElementException("No se pudo actualizar la solicitud"));
    }

    @Override
    public List<Acta> listarActasAdmin(String correoAdmin) {
        exigirMiembroDirectiva(correoAdmin);
        return actaDAO.listar();
    }

    @Override
    public List<Acta> listarActasPublicadas() {
        return actaDAO.listar().stream()
                .filter(acta -> ESTADO_PUBLICADA.equals(acta.getEstado()))
                .toList();
    }

    @Override
    public Acta crearActa(String correoAdmin, ActaRequestDTO request) {
        MiembroDirectiva admin = exigirEscrituraSecretaria(correoAdmin);

        if (request.titulo() == null || request.titulo().isBlank()) {
            throw new IllegalArgumentException("El titulo del acta es obligatorio");
        }
        if (request.contenido() == null || request.contenido().isBlank()) {
            throw new IllegalArgumentException("El contenido del acta es obligatorio");
        }

        String fecha = request.fecha() != null && !request.fecha().isBlank()
                ? request.fecha().trim()
                : LocalDate.now().toString();

        List<AcuerdoActa> acuerdos = mapearAcuerdos(request.acuerdos());

        Acta acta = new Acta(
                request.titulo().trim(),
                request.contenido().trim(),
                fecha,
                ESTADO_BORRADOR,
                correoAdmin,
                admin.getNombre(),
                acuerdos
        );

        return actaDAO.guardar(acta);
    }

    @Override
    public Acta publicarActa(String correoAdmin, String actaId) {
        exigirEscrituraSecretaria(correoAdmin);

        Acta existente = actaDAO.buscarPorId(actaId)
                .orElseThrow(() -> new NoSuchElementException("No existe el acta"));

        existente.setEstado(ESTADO_PUBLICADA);

        return actaDAO.actualizar(actaId, existente)
                .orElseThrow(() -> new NoSuchElementException("No se pudo publicar el acta"));
    }

    @Override
    public List<Comunicado> listarComunicados() {
        return comunicadoDAO.listar();
    }

    @Override
    public Comunicado crearComunicado(String correoAdmin, ComunicadoRequestDTO request) {
        MiembroDirectiva admin = exigirEscrituraSecretaria(correoAdmin);

        if (request.titulo() == null || request.titulo().isBlank()) {
            throw new IllegalArgumentException("El titulo del comunicado es obligatorio");
        }
        if (request.contenido() == null || request.contenido().isBlank()) {
            throw new IllegalArgumentException("El contenido del comunicado es obligatorio");
        }

        Comunicado comunicado = new Comunicado(
                request.titulo().trim(),
                request.contenido().trim(),
                LocalDate.now().toString(),
                correoAdmin,
                admin.getNombre()
        );

        return comunicadoDAO.guardar(comunicado);
    }

    private List<AcuerdoActa> mapearAcuerdos(List<AcuerdoActaDTO> acuerdos) {
        if (acuerdos == null || acuerdos.isEmpty()) {
            return new ArrayList<>();
        }

        List<AcuerdoActa> resultado = new ArrayList<>();
        for (AcuerdoActaDTO acuerdo : acuerdos) {
            if (acuerdo == null || acuerdo.texto() == null || acuerdo.texto().isBlank()) {
                continue;
            }
            String proyectoId = acuerdo.proyectoId() != null && !acuerdo.proyectoId().isBlank()
                    ? acuerdo.proyectoId().trim()
                    : null;
            resultado.add(new AcuerdoActa(acuerdo.texto().trim(), proyectoId));
        }
        return resultado;
    }

    private MiembroDirectiva exigirEscrituraSecretaria(String correoAdmin) {
        MiembroDirectiva miembro = exigirMiembroDirectiva(correoAdmin);
        String cargo = miembro.getCargo() == null ? "" : miembro.getCargo().trim().toUpperCase();

        if (!CARGOS_ESCRITURA.contains(cargo)) {
            throw new SecurityException("Solo Presidente o Secretario pueden escribir en secretaria");
        }

        return miembro;
    }

    private MiembroDirectiva exigirMiembroDirectiva(String correoAdmin) {
        Usuario usuario = usuarioService.buscarPorCorreo(correoAdmin)
                .orElseThrow(() -> new SecurityException("Usuario no autenticado"));

        if (!(usuario instanceof MiembroDirectiva miembro)) {
            throw new SecurityException("Se requiere un miembro de la directiva");
        }

        return miembro;
    }
}
