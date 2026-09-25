package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.controllers;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.EmailDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.RegistroEmail;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /** Responde 202 de inmediato; el correo se envía en segundo plano. */
    @PostMapping("/enviar")
    public ResponseEntity<Map<String, String>> enviar(@RequestBody EmailDTO email) {
        emailService.enviar(email);
        return ResponseEntity.accepted()
                .body(Map.of("mensaje", "Correo encolado para " + email.getDestinatario()));
    }

    @GetMapping("/historial")
    public List<RegistroEmail> historial() {
        return emailService.obtenerHistorial();
    }
}
