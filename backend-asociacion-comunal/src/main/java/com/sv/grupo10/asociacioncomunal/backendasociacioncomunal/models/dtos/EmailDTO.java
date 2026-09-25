package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.regex.Pattern;

/**
 * Datos mínimos para enviar un correo: destinatario, asunto y cuerpo.
 * Inmutable y validado en el constructor (encapsulamiento).
 */
public class EmailDTO {

    private static final Pattern PATRON_EMAIL =
            Pattern.compile("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");

    private final String destinatario;
    private final String asunto;
    private final String cuerpo;
    private final boolean html;

    @JsonCreator
    public EmailDTO(@JsonProperty("destinatario") String destinatario,
                    @JsonProperty("asunto") String asunto,
                    @JsonProperty("cuerpo") String cuerpo,
                    @JsonProperty("html") boolean html) {
        if (destinatario == null || !PATRON_EMAIL.matcher(destinatario.trim()).matches()) {
            throw new IllegalArgumentException("Correo destinatario inválido: " + destinatario);
        }
        if (asunto == null || asunto.isBlank()) {
            throw new IllegalArgumentException("El asunto es obligatorio");
        }
        if (cuerpo == null || cuerpo.isBlank()) {
            throw new IllegalArgumentException("El cuerpo es obligatorio");
        }
        this.destinatario = destinatario.trim();
        this.asunto = asunto.trim();
        this.cuerpo = cuerpo;
        this.html = html;
    }

    public EmailDTO(String destinatario, String asunto, String cuerpo) {
        this(destinatario, asunto, cuerpo, false);
    }

    public String getDestinatario() { return destinatario; }
    public String getAsunto()       { return asunto; }
    public String getCuerpo()       { return cuerpo; }
    public boolean isHtml()         { return html; }
}
