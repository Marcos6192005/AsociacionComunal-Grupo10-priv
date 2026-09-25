package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.controllers;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.AuthRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.MiembroDirectiva;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Usuario;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.security.JwtProvider;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UsuarioService  usuarioService;
    private final JwtProvider jwtProvider;

    public AuthController(UsuarioService usuarioService, JwtProvider jwtProvider) {
        this.usuarioService = usuarioService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login (@RequestBody AuthRequestDTO request)  {
        Optional<Usuario> usuarioAutenticado = usuarioService.autenticar(request.correo(), request.password());
        Map<String, Object> response = new HashMap<>();

        if (usuarioAutenticado.isPresent()){
            Usuario usuario = usuarioAutenticado.get();

            String token = jwtProvider.generarToken(usuario.getCorreo(), usuario.getRol());

            String cargo = null;
            if (usuario instanceof MiembroDirectiva miembro) {
                cargo = miembro.getCargo();
            }

            response.put("mensaje", "Inicio exitoso");
            response.put("token", token);
            response.put("status", "OK");
            response.put("id", usuario.getId());
            response.put("nombre", usuario.getNombre());
            response.put("rol", usuario.getRol());
            response.put("cargo", cargo);

            return ResponseEntity.ok(response);
        }else{
            response.put("mensaje", "Credenciales incorrectas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
