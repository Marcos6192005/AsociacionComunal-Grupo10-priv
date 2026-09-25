package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.plantillas;

public class EmailBienvenida extends PlantillaEmail {

    private final String nombreUsuario;

    public EmailBienvenida(String destinatario, String nombreUsuario) {
        super(destinatario);
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    protected String construirAsunto() {
        return "Bienvenido/a a la Asociación Comunal";
    }

    @Override
    protected String construirCuerpo() {
        return envolverHtml(
                "¡Hola, " + escapar(nombreUsuario) + "!",
                "<p>Tu cuenta fue creada correctamente. Ya puedes iniciar sesión en el sistema.</p>");
    }
}
