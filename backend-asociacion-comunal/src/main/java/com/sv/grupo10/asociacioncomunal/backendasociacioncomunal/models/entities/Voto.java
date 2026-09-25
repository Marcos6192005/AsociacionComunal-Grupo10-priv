package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities;

import java.io.Serializable;
import java.util.UUID;

public class Voto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String proyectoId;
    private String correoUsuario;
    private String valor;

    public Voto() {
        this.id = UUID.randomUUID().toString();
    }

    public Voto(String proyectoId, String correoUsuario, String valor) {
        this.id = UUID.randomUUID().toString();
        this.proyectoId = proyectoId;
        this.correoUsuario = correoUsuario;
        this.valor = valor;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getProyectoId() { return proyectoId; }
    public void setProyectoId(String proyectoId) { this.proyectoId = proyectoId; }

    public String getCorreoUsuario() { return correoUsuario; }
    public void setCorreoUsuario(String correoUsuario) { this.correoUsuario = correoUsuario; }

    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }
}
