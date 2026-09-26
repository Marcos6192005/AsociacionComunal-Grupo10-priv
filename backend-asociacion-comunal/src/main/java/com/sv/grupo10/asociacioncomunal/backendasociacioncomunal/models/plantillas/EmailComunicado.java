package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.plantillas;

public class EmailComunicado extends PlantillaEmail {

    private final String titulo;
    private final String contenido;

    public EmailComunicado(String destinatario, String titulo, String contenido) {
        super(destinatario);
        this.titulo = titulo;
        this.contenido = contenido;
    }

    @Override
    protected String construirAsunto() {
        return "Comunicado de la asociación: " + (titulo == null ? "" : titulo);
    }

    @Override
    protected String construirCuerpo() {
        return envolverHtml(
                escapar(titulo),
                "<p>" + escapar(contenido) + "</p>");
    }
}
