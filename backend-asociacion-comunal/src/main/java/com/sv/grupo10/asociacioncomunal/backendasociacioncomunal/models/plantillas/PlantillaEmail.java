package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.plantillas;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.EmailDTO;

/**
 * Clase abstracta base para correos con formato predefinido.
 * Cada subclase decide su asunto y cuerpo (polimorfismo);
 * generar() es el método plantilla que arma el EmailDTO final.
 */
public abstract class PlantillaEmail {

    private final String destinatario;

    protected PlantillaEmail(String destinatario) {
        this.destinatario = destinatario;
    }

    protected abstract String construirAsunto();

    protected abstract String construirCuerpo();

    protected boolean esHtml() {
        return true;
    }

    public final EmailDTO generar() {
        return new EmailDTO(destinatario, construirAsunto(), construirCuerpo(), esHtml());
    }

    public String getDestinatario() {
        return destinatario;
    }

    /** Estructura HTML común para todas las plantillas. */
    protected String envolverHtml(String titulo, String contenido) {
        return """
                <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto;">
                  <h2 style="color: #1f4e79;">%s</h2>
                  %s
                  <hr>
                  <p style="font-size: 12px; color: #777;">
                    Este es un mensaje automático de la Asociación Comunal. No respondas a este correo.
                  </p>
                </div>
                """.formatted(titulo, contenido);
    }

    /** Evita que datos del usuario rompan o inyecten HTML en el correo. */
    protected static String escapar(String texto) {
        if (texto == null) return "";
        return texto.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;");
    }
}
