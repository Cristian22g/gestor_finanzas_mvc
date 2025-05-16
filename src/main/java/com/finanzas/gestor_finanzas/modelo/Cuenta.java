package com.finanzas.gestor_finanzas.modelo;


import com.finanzas.gestor_finanzas.excepciones.NombreCuentaException;
import com.finanzas.gestor_finanzas.validaciones.Validaciones;

public class Cuenta {
    private int id;
    private int idUsuario;
    private String nombreCuenta;
    private double saldoActual;
    

    public Cuenta() {}
    
    // CONSTRUCTOR COMPLETO
    public Cuenta(int id, int idUsuario, String nombreCuenta, double saldoActual) throws NombreCuentaException {
       	setId(id);
        setIdUsuario(idUsuario);
        setNombreCuenta(nombreCuenta);
        setSaldoActual(saldoActual);
    }

    // CONSTRUCTOR SIN ID (a 0)
    public Cuenta(int idUsuario, String nombreCuenta, double saldoActual) throws NombreCuentaException {
        this(0, idUsuario, nombreCuenta, saldoActual);
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreCuenta(String nombreCuenta) throws NombreCuentaException {
    	if(!Validaciones.validarNombreCuenta(nombreCuenta)) throw new NombreCuentaException("El nombre de la cuenta debe ser de entre 4 y 20 carácteres de letras/números.");
        this.nombreCuenta = nombreCuenta;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    @Override
    public String toString() {
        return nombreCuenta + " (€" + saldoActual + ")";
    }
}
