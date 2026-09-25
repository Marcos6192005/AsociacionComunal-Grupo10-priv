package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.security.Roles;

public class MiembroDirectiva extends Usuario {

    private static final long serialVersionUID = 1L;
    private String cargo;

    public MiembroDirectiva(String nombre, String correo, String password, String cargo){
        super(nombre, correo, password);
        this.cargo = cargo;
    }

    @Override
    public String getRol(){
        return Roles.rolDesdeCargo(cargo);
    }

    public String getCargo(){return cargo;}
    public void setCargo(String cargo){this.cargo = cargo;}
}
