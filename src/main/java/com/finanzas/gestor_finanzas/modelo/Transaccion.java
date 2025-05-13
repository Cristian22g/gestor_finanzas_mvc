package com.finanzas.gestor_finanzas.modelo;

import java.time.LocalDate;

public class Transaccion {
    private int id;
    private int idUsuario;
    private double monto;
    private String tipo; // INGRESO o GASTO
    private String categoria;
    private String descripcion;
    private LocalDate fecha;


    // CONSTRUCTOR COMPLETO
    public Transaccion(int id, int idUsuario, double monto, String tipo,
                       String categoria, String descripcion, LocalDate fecha) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.monto = monto;
        this.tipo = tipo;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    // CONSTRUCTOR SIN ID (a 0)
    public Transaccion(int idUsuario, double monto, String tipo,
                       String categoria, String descripcion, LocalDate fecha) {
        this(0, idUsuario, monto, tipo, categoria, descripcion, fecha);
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public double getMonto() {
        return monto;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return tipo + ": " + monto + " (" + categoria + ")";
    }
}
