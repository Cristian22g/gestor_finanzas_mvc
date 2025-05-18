package com.finanzas.gestor_finanzas.modelo;

import com.finanzas.gestor_finanzas.excepciones.CantidadException;

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
                       String categoria, String descripcion, LocalDate fecha) throws CantidadException {
        setId(id);
        setIdUsuario(idUsuario);
        setMonto(monto);
        setTipo(tipo);
        setCategoria(categoria);
        setDescripcion(descripcion);
        setFecha(fecha);
    }

    // CONSTRUCTOR SIN ID (a 0)
    public Transaccion(int idUsuario, double monto, String tipo,
                       String categoria, String descripcion, LocalDate fecha) throws CantidadException {
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

    public void setMonto(double monto) throws  CantidadException{
        if(monto < 0) throw new CantidadException("EL monto no puede ser inferior a 0.");
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
