package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities;

import java.io.Serializable;
import java.util.UUID;

public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombre;
    private String descripcion;
    private String estado;
    private String fechaInicio;

    public Proyecto() {
        this.id = UUID.randomUUID().toString();
    }

    public Proyecto(String nombre, String descripcion, String estado, String fechaInicio) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
    }

    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}

    public String getDescripcion(){return descripcion;}
    public void setDescripcion(String descripcion){this.descripcion = descripcion;}

    public String getEstado(){return estado;}
    public void setEstado(String estado){this.estado = estado;}

    public String getFechaInicio(){return fechaInicio;}
    public void setFechaInicio(String fechaInicio){this.fechaInicio = fechaInicio;}
}
