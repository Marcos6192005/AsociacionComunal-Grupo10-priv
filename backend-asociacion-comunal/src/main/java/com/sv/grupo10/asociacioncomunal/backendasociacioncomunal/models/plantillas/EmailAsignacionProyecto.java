package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.plantillas;

public class EmailAsignacionProyecto extends PlantillaEmail {

    private final String nombreUsuario;
    private final String nombreProyecto;

    public EmailAsignacionProyecto(String destinatario, String nombreUsuario, String nombreProyecto) {
        super(destinatario);
        this.nombreUsuario = nombreUsuario;
        this.nombreProyecto = nombreProyecto;
    }

    @Override
    protected String construirAsunto() {
        return "Asignación al proyecto: " + nombreProyecto;
    }

    @Override
    protected String construirCuerpo() {
        return envolverHtml(
                "Nuevo proyecto asignado",
                "<p>Hola " + escapar(nombreUsuario) + ", has sido asignado/a al proyecto <b>"
                        + escapar(nombreProyecto) + "</b>.</p>"
                        + "<p>Ingresa al sistema para ver los detalles.</p>");
    }
}
