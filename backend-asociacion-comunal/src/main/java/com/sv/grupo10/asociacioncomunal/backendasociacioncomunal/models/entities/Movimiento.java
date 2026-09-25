package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities;

import java.io.Serializable;
import java.util.UUID;

public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String tipo;
    private String concepto;
    private double monto;
    private String fecha;
    private String proyectoId;
    private String correoRegistro;
    private String nombreRegistro;
    private String cuotaId;

    public Movimiento() {
        this.id = UUID.randomUUID().toString();
    }

    public Movimiento(
            String tipo,
            String concepto,
            double monto,
            String fecha,
            String proyectoId,
            String correoRegistro,
            String nombreRegistro
    ) {
        this.id = UUID.randomUUID().toString();
        this.tipo = tipo;
        this.concepto = concepto;
        this.monto = monto;
        this.fecha = fecha;
        this.proyectoId = proyectoId;
        this.correoRegistro = correoRegistro;
        this.nombreRegistro = nombreRegistro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(String proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getCorreoRegistro() {
        return correoRegistro;
    }

    public void setCorreoRegistro(String correoRegistro) {
        this.correoRegistro = correoRegistro;
    }

    public String getNombreRegistro() {
        return nombreRegistro;
    }

    public void setNombreRegistro(String nombreRegistro) {
        this.nombreRegistro = nombreRegistro;
    }

    public String getCuotaId() {
        return cuotaId;
    }

    public void setCuotaId(String cuotaId) {
        this.cuotaId = cuotaId;
    }
}
