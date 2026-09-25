package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities;

import java.io.Serializable;
import java.util.UUID;

public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String titulo;
    private String descripcion;
    private String correoAutor;
    private String nombreAutor;
    private String estado;
    private String respuesta;
    private String proyectoId;
    private String fecha;

    public Solicitud() {
        this.id = UUID.randomUUID().toString();
    }

    public Solicitud(
            String titulo,
            String descripcion,
            String correoAutor,
            String nombreAutor,
            String estado,
            String fecha
    ) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.correoAutor = correoAutor;
        this.nombreAutor = nombreAutor;
        this.estado = estado;
        this.fecha = fecha;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(String proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
