package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Acta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String titulo;
    private String contenido;
    private String fecha;
    private String estado;
    private String correoAutor;
    private String nombreAutor;
    private List<AcuerdoActa> acuerdos = new ArrayList<>();

    public Acta() {
        this.id = UUID.randomUUID().toString();
    }

    public Acta(
            String titulo,
            String contenido,
            String fecha,
            String estado,
            String correoAutor,
            String nombreAutor,
            List<AcuerdoActa> acuerdos
    ) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.estado = estado;
        this.correoAutor = correoAutor;
        this.nombreAutor = nombreAutor;
        this.acuerdos = acuerdos != null ? acuerdos : new ArrayList<>();
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public List<AcuerdoActa> getAcuerdos() {
        return acuerdos;
    }

    public void setAcuerdos(List<AcuerdoActa> acuerdos) {
        this.acuerdos = acuerdos != null ? acuerdos : new ArrayList<>();
    }
}
