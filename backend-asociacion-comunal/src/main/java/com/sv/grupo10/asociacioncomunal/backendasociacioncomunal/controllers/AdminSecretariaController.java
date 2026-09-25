package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.controllers;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ActaRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ComunicadoRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ResponderSolicitudRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Acta;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Comunicado;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Solicitud;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.SecretariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/admin/secretaria")
public class AdminSecretariaController {

    private final SecretariaService secretariaService;

    public AdminSecretariaController(SecretariaService secretariaService) {
        this.secretariaService = secretariaService;
    }

    @GetMapping("/solicitudes")
    public ResponseEntity<List<Solicitud>> listarSolicitudes() {
        return ResponseEntity.ok(secretariaService.listarSolicitudes());
    }

    @PutMapping("/solicitudes/{id}/responder")
    public ResponseEntity<?> responderSolicitud(
            @PathVariable String id,
            @RequestBody ResponderSolicitudRequestDTO request,
            Authentication authentication
    ) {
        try {
            Solicitud actualizada = secretariaService.responderSolicitud(
                    authentication.getName(),
                    id,
                    request
            );
            return ResponseEntity.ok(actualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("mensaje", e.getMessage()));
        }
    }

    @GetMapping("/actas")
    public ResponseEntity<?> listarActas(Authentication authentication) {
        try {
            return ResponseEntity.ok(secretariaService.listarActasAdmin(authentication.getName()));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("mensaje", e.getMessage()));
        }
    }

    @PostMapping("/actas")
    public ResponseEntity<?> crearActa(
            @RequestBody ActaRequestDTO request,
            Authentication authentication
    ) {
        try {
            Acta creada = secretariaService.crearActa(authentication.getName(), request);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("mensaje", e.getMessage()));
        }
    }

    @PutMapping("/actas/{id}/publicar")
    public ResponseEntity<?> publicarActa(
            @PathVariable String id,
            Authentication authentication
    ) {
        try {
            Acta publicada = secretariaService.publicarActa(authentication.getName(), id);
            return ResponseEntity.ok(publicada);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("mensaje", e.getMessage()));
        }
    }

    @GetMapping("/comunicados")
    public ResponseEntity<List<Comunicado>> listarComunicados() {
        return ResponseEntity.ok(secretariaService.listarComunicados());
    }

    @PostMapping("/comunicados")
    public ResponseEntity<?> crearComunicado(
            @RequestBody ComunicadoRequestDTO request,
            Authentication authentication
    ) {
        try {
            Comunicado creado = secretariaService.crearComunicado(authentication.getName(), request);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("mensaje", e.getMessage()));
        }
    }
}
