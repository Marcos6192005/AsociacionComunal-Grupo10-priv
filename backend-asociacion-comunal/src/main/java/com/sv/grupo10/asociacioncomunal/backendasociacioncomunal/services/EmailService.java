package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.EmailDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.plantillas.PlantillaEmail;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.RegistroEmail;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Servicio de envío de correos. Todos los métodos regresan de inmediato:
 * el envío real ocurre en un hilo aparte.
 */
public interface EmailService {

    /** Uso más simple: se puede llamar desde cualquier parte del sistema. */
    CompletableFuture<RegistroEmail> enviar(String destinatario, String asunto, String cuerpo);

    CompletableFuture<RegistroEmail> enviar(EmailDTO email);

    CompletableFuture<RegistroEmail> enviar(PlantillaEmail plantilla);

    List<CompletableFuture<RegistroEmail>> enviarMasivo(List<EmailDTO> emails);

    List<RegistroEmail> obtenerHistorial();
}
