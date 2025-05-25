package com.finanzas.gestor_finanzas.modelo;


import com.finanzas.gestor_finanzas.excepciones.CantidadException;
import com.finanzas.gestor_finanzas.excepciones.NombreCuentaException;
import com.finanzas.gestor_finanzas.validaciones.Validaciones;

/**
 * Clase que representa una cuenta financiera.
 */
public class Cuenta {

    /**
     * Identificador único de la cuenta.
     */
    private int id;

    /**
     * Id del usuario propietario de la cuenta.
     */
    private int idUsuario;

    /**
     * Nombre de la cuenta (por ejemplo: "Cuenta corriente", "Ahorros").
     */
    private String nombreCuenta;

    /**
     * Saldo actual disponible en la cuenta.
     */
    private double saldoActual;

    public Cuenta() {
    }

    /**
     * Constructor completo de una cuenta con ID especificado.
     *
     * @param id Identificador único de la cuenta.
     * @param idUsuario ID del usuario propietario de la cuenta.
     * @param nombreCuenta Nombre asignado a la cuenta (por ejemplo, "Ahorros", "Nomina").
     * @param saldoActual Saldo actual disponible en la cuenta.
     * @throws NombreCuentaException Si el nombre de la cuenta no es válido (vacío, nulo o duplicado según reglas del sistema).
     * @throws CantidadException Si el saldo inicial no es válido (por ejemplo, negativo).
     */
    public Cuenta(int id, int idUsuario, String nombreCuenta, double saldoActual) throws NombreCuentaException, CantidadException {
       	setId(id);
        setIdUsuario(idUsuario);
        setNombreCuenta(nombreCuenta);
        setSaldoActual(saldoActual);
    }

    /**
     * Constructor de una cuenta sin ID especificado (por defecto se asigna 0).
     *
     * @param idUsuario ID del usuario propietario.
     * @param nombreCuenta Nombre de la cuenta.
     * @param saldoActual Saldo inicial.
     * @throws NombreCuentaException Si el nombre de la cuenta no es válido.
     * @throws CantidadException Si el saldo inicial no es válido.
     */
    public Cuenta(int idUsuario, String nombreCuenta, double saldoActual) throws NombreCuentaException, CantidadException {
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

    public void setSaldoActual(double saldoActual) throws CantidadException {
        if(saldoActual < 0) throw new CantidadException("El saldo no puede ser inferior a 0");
        this.saldoActual = saldoActual;
    }

    @Override
    public String toString() {
        return nombreCuenta + " (€" + saldoActual + ")";
    }
}
