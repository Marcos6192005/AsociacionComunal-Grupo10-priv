package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.EmailDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.plantillas.PlantillaEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Enqueues domain notifications without waiting for SMTP.
 * A failure here must not change the HTTP result of the business write.
 */
@Component
public class NotificacionCorreo {

    private static final Logger log = LoggerFactory.getLogger(NotificacionCorreo.class);

    private final EmailService emailService;

    public NotificacionCorreo(EmailService emailService) {
        this.emailService = emailService;
    }

    public void avisar(PlantillaEmail plantilla) {
        if (plantilla == null || esVacio(plantilla.getDestinatario())) {
            return;
        }
        try {
            emailService.enviar(plantilla);
        } catch (RuntimeException ex) {
            log.warn("Could not enqueue mail to {}: {}", plantilla.getDestinatario(), ex.getMessage());
        }
    }

    public void avisarVarios(List<PlantillaEmail> plantillas) {
        if (plantillas == null || plantillas.isEmpty()) {
            return;
        }

        List<EmailDTO> emails = new ArrayList<>();
        for (PlantillaEmail plantilla : plantillas) {
            if (plantilla == null || esVacio(plantilla.getDestinatario())) {
                continue;
            }
            try {
                emails.add(plantilla.generar());
            } catch (RuntimeException ex) {
                log.warn("Skipped mail to {}: {}", plantilla.getDestinatario(), ex.getMessage());
            }
        }

        if (emails.isEmpty()) {
            return;
        }

        try {
            emailService.enviarMasivo(emails);
        } catch (RuntimeException ex) {
            log.warn("Could not enqueue bulk mail: {}", ex.getMessage());
        }
    }

    private static boolean esVacio(String correo) {
        return correo == null || correo.isBlank();
    }
}
