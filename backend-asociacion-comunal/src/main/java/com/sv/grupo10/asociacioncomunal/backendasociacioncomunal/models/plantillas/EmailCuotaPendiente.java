package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.plantillas;

import java.util.Locale;

public class EmailCuotaPendiente extends PlantillaEmail {

    private final String periodo;
    private final double monto;
    private final String nombreVecino;

    public EmailCuotaPendiente(String destinatario, String periodo, double monto, String nombreVecino) {
        super(destinatario);
        this.periodo = periodo;
        this.monto = monto;
        this.nombreVecino = nombreVecino;
    }

    @Override
    protected String construirAsunto() {
        return "Cuota pendiente " + (periodo == null ? "" : periodo);
    }

    @Override
    protected String construirCuerpo() {
        return envolverHtml(
                "Tienes una cuota pendiente",
                "<p>Hola, " + escapar(nombreVecino) + ".</p>"
                        + "<p>Periodo <strong>" + escapar(periodo) + "</strong>: $"
                        + String.format(Locale.US, "%.2f", monto) + ".</p>");
    }
}
