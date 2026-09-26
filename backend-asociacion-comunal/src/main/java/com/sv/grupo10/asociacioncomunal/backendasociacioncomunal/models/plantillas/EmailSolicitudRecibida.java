package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.plantillas;

public class EmailSolicitudRecibida extends PlantillaEmail {

    private final String titulo;
    private final String nombreAutor;

    public EmailSolicitudRecibida(String destinatario, String titulo, String nombreAutor) {
        super(destinatario);
        this.titulo = titulo;
        this.nombreAutor = nombreAutor;
    }

    @Override
    protected String construirAsunto() {
        return "Nueva solicitud de la comunidad";
    }

    @Override
    protected String construirCuerpo() {
        return envolverHtml(
                "Llegó una solicitud",
                "<p><strong>" + escapar(nombreAutor) + "</strong> envió: "
                        + escapar(titulo) + ".</p>"
                        + "<p>Revisala en el módulo de secretaría.</p>");
    }
}
