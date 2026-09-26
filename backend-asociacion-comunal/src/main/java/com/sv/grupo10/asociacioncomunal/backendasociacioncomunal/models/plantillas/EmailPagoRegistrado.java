package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.plantillas;

import java.util.Locale;

public class EmailPagoRegistrado extends PlantillaEmail {

    private final String periodo;
    private final double monto;
    private final String fechaPago;

    public EmailPagoRegistrado(String destinatario, String periodo, double monto, String fechaPago) {
        super(destinatario);
        this.periodo = periodo;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

    @Override
    protected String construirAsunto() {
        return "Pago de cuota registrado " + (periodo == null ? "" : periodo);
    }

    @Override
    protected String construirCuerpo() {
        return envolverHtml(
                "Registramos tu pago",
                "<p>La cuota del periodo <strong>" + escapar(periodo) + "</strong> quedó pagada.</p>"
                        + "<p>Monto: $" + String.format(Locale.US, "%.2f", monto)
                        + ". Fecha: " + escapar(fechaPago) + ".</p>");
    }
}
