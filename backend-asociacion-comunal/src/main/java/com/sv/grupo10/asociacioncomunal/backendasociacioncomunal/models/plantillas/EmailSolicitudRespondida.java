package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.plantillas;

public class EmailSolicitudRespondida extends PlantillaEmail {

    private final String titulo;
    private final String estado;
    private final String respuesta;

    public EmailSolicitudRespondida(String destinatario, String titulo, String estado, String respuesta) {
        super(destinatario);
        this.titulo = titulo;
        this.estado = estado;
        this.respuesta = respuesta;
    }

    @Override
    protected String construirAsunto() {
        return "Tu solicitud fue respondida";
    }

    @Override
    protected String construirCuerpo() {
        String textoRespuesta = respuesta == null || respuesta.isBlank()
                ? "La junta no dejó un comentario adicional."
                : escapar(respuesta);
        return envolverHtml(
                "Respuesta a tu solicitud",
                "<p>La solicitud <strong>" + escapar(titulo) + "</strong> quedó en estado "
                        + escapar(estado) + ".</p>"
                        + "<p>" + textoRespuesta + "</p>");
    }
}
