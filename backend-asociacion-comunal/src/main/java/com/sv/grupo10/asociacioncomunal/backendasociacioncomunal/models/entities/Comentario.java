package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities;

import java.io.Serializable;
import java.util.UUID;

public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String proyectoId;
    private String correoAutor;
    private String nombreAutor;
    private String texto;
    private String fecha;

    public Comentario() {
        this.id = UUID.randomUUID().toString();
    }

    public Comentario(String proyectoId, String correoAutor, String nombreAutor, String texto, String fecha) {
        this.id = UUID.randomUUID().toString();
        this.proyectoId = proyectoId;
        this.correoAutor = correoAutor;
        this.nombreAutor = nombreAutor;
        this.texto = texto;
        this.fecha = fecha;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getProyectoId() { return proyectoId; }
    public void setProyectoId(String proyectoId) { this.proyectoId = proyectoId; }

    public String getCorreoAutor() { return correoAutor; }
    public void setCorreoAutor(String correoAutor) { this.correoAutor = correoAutor; }

    public String getNombreAutor() { return nombreAutor; }
    public void setNombreAutor(String nombreAutor) { this.nombreAutor = nombreAutor; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
