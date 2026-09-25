package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.controllers;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.UsuarioRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.UsuarioResponseDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.MiembroDirectiva;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Usuario;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Vecino;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios().stream()
                .map(UsuarioResponseDTO::desde)
                .toList();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        return usuarioService.buscarPorId(id)
                .<ResponseEntity<?>>map(usuario -> ResponseEntity.ok(UsuarioResponseDTO.desde(usuario)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "No existe un usuario con id " + id)));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody UsuarioRequestDTO request) {
        try {
            Usuario usuario = mapearUsuario(request);
            Usuario creado = usuarioService.crearUsuario(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponseDTO.desde(creado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestBody UsuarioRequestDTO request) {
        try {
            Usuario existente = usuarioService.buscarPorId(id)
                    .orElseThrow(() -> new NoSuchElementException("No existe un usuario con id " + id));

            Usuario usuario = mapearUsuario(request);
            usuario.setId(id);

            // Si no envían password nueva, conservamos la actual
            if (request.password() == null || request.password().isBlank()) {
                usuario.setPassword(existente.getPassword());
            }

            Usuario actualizado = usuarioService.actualizarUsuario(usuario);
            return ResponseEntity.ok(UsuarioResponseDTO.desde(actualizado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", e.getMessage()));
        }
    }

    private Usuario mapearUsuario(UsuarioRequestDTO request) {
        if (request.tipo() == null) {
            throw new IllegalArgumentException("Debes indicar el tipo de usuario (VECINO o DIRECTIVA)");
        }

        return switch (request.tipo().toUpperCase()) {
            case "VECINO" -> new Vecino(request.nombre(), request.correo(), request.password(), request.numeroCasa());
            case "DIRECTIVA", "ADMINISTRACION" ->
                    new MiembroDirectiva(request.nombre(), request.correo(), request.password(), request.cargo());
            default -> throw new IllegalArgumentException("Tipo de usuario no válido: " + request.tipo());
        };
    }
}