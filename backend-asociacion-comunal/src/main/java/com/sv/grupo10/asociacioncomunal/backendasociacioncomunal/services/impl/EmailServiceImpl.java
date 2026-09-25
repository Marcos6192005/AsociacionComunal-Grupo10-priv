package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.impl;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.dao.interfaces.EmailLogDAO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.EmailDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.plantillas.PlantillaEmail;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.RegistroEmail;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.EmailService;
import jakarta.annotation.PreDestroy;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Envía correos por SMTP usando un pool fijo de hilos.
 *
 * Por qué hilos: una conexión SMTP tarda de 1 a varios segundos (y más si
 * el servidor está lento o hay reintentos). Si se enviara en el hilo de la
 * petición HTTP, el usuario tendría que esperar ese tiempo para, por ejemplo,
 * registrarse. Con el pool, la petición responde de inmediato y el correo se
 * envía en segundo plano; además, un envío masivo se reparte entre varios hilos.
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
    private static final int MAX_INTENTOS = 3;
    private static final long ESPERA_BASE_MS = 2000;

    private final JavaMailSender mailSender;
    private final EmailLogDAO emailLogDAO;
    private final String remitente;
    private final ExecutorService executor;

    public EmailServiceImpl(JavaMailSender mailSender,
                            EmailLogDAO emailLogDAO,
                            @Value("${app.mail.remitente}") String remitente,
                            @Value("${app.mail.hilos:3}") int cantidadHilos) {
        this.mailSender = mailSender;
        this.emailLogDAO = emailLogDAO;
        this.remitente = remitente;
        this.executor = Executors.newFixedThreadPool(cantidadHilos, new EmailThreadFactory());
    }

    @Override
    public CompletableFuture<RegistroEmail> enviar(String destinatario, String asunto, String cuerpo) {
        return enviar(new EmailDTO(destinatario, asunto, cuerpo));
    }

    @Override
    public CompletableFuture<RegistroEmail> enviar(PlantillaEmail plantilla) {
        return enviar(plantilla.generar());
    }

    @Override
    public CompletableFuture<RegistroEmail> enviar(EmailDTO email) {
        RegistroEmail registro = new RegistroEmail(email.getDestinatario(), email.getAsunto());
        emailLogDAO.guardar(registro);
        log.info("Correo encolado [{}] para {}", registro.getId(), email.getDestinatario());
        return CompletableFuture.supplyAsync(() -> procesarEnvio(email, registro), executor);
    }

    @Override
    public List<CompletableFuture<RegistroEmail>> enviarMasivo(List<EmailDTO> emails) {
        return emails.stream().map(this::enviar).toList();
    }

    @Override
    public List<RegistroEmail> obtenerHistorial() {
        return emailLogDAO.listarTodos();
    }

    /** Se ejecuta dentro de un hilo del pool. Reintenta con espera creciente. */
    private RegistroEmail procesarEnvio(EmailDTO email, RegistroEmail registro) {
        String hilo = Thread.currentThread().getName();
        try {
            for (int intento = 1; intento <= MAX_INTENTOS; intento++) {
                registro.registrarIntento(hilo);
                try {
                    mailSender.send(construirMensaje(email));
                    registro.marcarEnviado();
                    log.info("[{}] Correo {} enviado a {} (intento {})",
                            hilo, registro.getId(), email.getDestinatario(), intento);
                    return registro;
                } catch (MailException | MessagingException e) {
                    registro.registrarError(e.getMessage());
                    log.warn("[{}] Falló intento {}/{} del correo {}: {}",
                            hilo, intento, MAX_INTENTOS, registro.getId(), e.getMessage());
                    if (intento < MAX_INTENTOS) {
                        Thread.sleep(ESPERA_BASE_MS * intento);
                    }
                }
            }
            registro.marcarFallido("Se agotaron los " + MAX_INTENTOS + " intentos: "
                    + registro.getUltimoError());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            registro.marcarFallido("Envío interrumpido al apagar la aplicación");
        } finally {
            emailLogDAO.actualizar(registro);
        }
        return registro;
    }

    private MimeMessage construirMensaje(EmailDTO email) throws MessagingException {
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, "UTF-8");
        helper.setFrom(remitente);
        helper.setTo(email.getDestinatario());
        helper.setSubject(email.getAsunto());
        helper.setText(email.getCuerpo(), email.isHtml());
        return mensaje;
    }

    /** Al apagar la app, deja terminar los correos pendientes (máx. 30 s). */
    @PreDestroy
    void apagar() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /** Pone nombres legibles a los hilos (email-sender-1, 2, ...) para los logs. */
    private static class EmailThreadFactory implements ThreadFactory {
        private final AtomicInteger contador = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable tarea) {
            Thread t = new Thread(tarea, "email-sender-" + contador.getAndIncrement());
            t.setDaemon(false);
            return t;
        }
    }
}
