package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities;

public class Vecino extends Usuario{
    private static final long serialVersionUID = 1L;
    private String numeroCasa;

    public Vecino(String nombre, String correo, String password, String numeroCasa){
        super(nombre, correo, password);
        this.numeroCasa = numeroCasa;
    }

    @Override
    public String getRol(){
        return "VECINO";
    }

    public String getNumeroCasa(){return numeroCasa;}
    public void setNumeroCasa(String numeroCasa){this.numeroCasa = numeroCasa;}
}
