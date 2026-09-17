package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities;

import java.io.Serializable;
import java.util.UUID;

public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombre;
    private String correo;
    private String password;

    public Usuario(String nombre, String correo, String password) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public abstract String getRol();

    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public String getNombre(){return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getCorreo(){return correo;}
    public void setCorreo(String correo){this.correo = correo;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

}
