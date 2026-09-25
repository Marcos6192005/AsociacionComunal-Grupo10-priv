package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities;

import java.io.Serializable;
import java.util.UUID;

public class Comunicado implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String titulo;
    private String contenido;
    private String fecha;
    private String correoAutor;
    private String nombreAutor;

    public Comunicado() {
        this.id = UUID.randomUUID().toString();
    }

    public Comunicado(
            String titulo,
            String contenido,
            String fecha,
            String correoAutor,
            String nombreAutor
    ) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.correoAutor = correoAutor;
        this.nombreAutor = nombreAutor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCorreoAutor() {
        return correoAutor;
    }

    public void setCorreoAutor(String correoAutor) {
        this.correoAutor = correoAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }
}
