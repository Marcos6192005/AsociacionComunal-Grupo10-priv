package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities;

import java.io.Serializable;

public class AcuerdoActa implements Serializable {

    private static final long serialVersionUID = 1L;

    private String texto;
    private String proyectoId;

    public AcuerdoActa() {
    }

    public AcuerdoActa(String texto, String proyectoId) {
        this.texto = texto;
        this.proyectoId = proyectoId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(String proyectoId) {
        this.proyectoId = proyectoId;
    }
}
