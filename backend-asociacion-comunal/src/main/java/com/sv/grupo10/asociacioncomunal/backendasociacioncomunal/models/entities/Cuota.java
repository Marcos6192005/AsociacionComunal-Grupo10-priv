package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities;

import java.io.Serializable;
import java.util.UUID;

public class Cuota implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String correoVecino;
    private String nombreVecino;
    private String numeroCasa;
    private String periodo;
    private double monto;
    private String estado;
    private String fechaPago;

    public Cuota() {
        this.id = UUID.randomUUID().toString();
    }

    public Cuota(
            String correoVecino,
            String nombreVecino,
            String numeroCasa,
            String periodo,
            double monto,
            String estado
    ) {
        this.id = UUID.randomUUID().toString();
        this.correoVecino = correoVecino;
        this.nombreVecino = nombreVecino;
        this.numeroCasa = numeroCasa;
        this.periodo = periodo;
        this.monto = monto;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreoVecino() {
        return correoVecino;
    }

    public void setCorreoVecino(String correoVecino) {
        this.correoVecino = correoVecino;
    }

    public String getNombreVecino() {
        return nombreVecino;
    }

    public void setNombreVecino(String nombreVecino) {
        this.nombreVecino = nombreVecino;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }
}
