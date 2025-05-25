package com.finanzas.gestor_finanzas.modelo;

import com.finanzas.gestor_finanzas.excepciones.CantidadException;

import java.time.LocalDate;

/**
 * Clase que representa una transacción financiera.
 */
public class Transaccion {

    /**
     * Identificador único de la transacción.
     */
    private int id;

    /**
     * Id del Usuario al que pertenece la transacción.
     */
    private int idUsuario;

    /**
     * Monto de la transacción (positivo o negativo).
     */
    private double monto;

    /**
     * Tipo de transacción (por ejemplo: "ingreso", "gasto").
     */
    private String tipo; // INGRESO o GASTO

    /**
     * Categoría de la transacción (por ejemplo: "comida", "transporte").
     */
    private String categoria;

    /**
     * Descripción detallada de la transacción.
     */
    private String descripcion;

    /**
     * Fecha en la que se realizó la transacción.
     */
    private LocalDate fecha;

    private int idCuenta;

    /**
     * Constructor de una transacción con ID especificado.
     *
     * @param id Identificador único de la transacción.
     * @param idUsuario ID del usuario al que pertenece la transacción.
     * @param monto Monto de la transacción (puede ser positivo o negativo según el tipo).
     * @param tipo Tipo de transacción (por ejemplo, "ingreso" o "gasto").
     * @param categoria Categoría asociada a la transacción (por ejemplo, "comida", "transporte").
     * @param descripcion Descripción opcional de la transacción.
     * @param fecha Fecha en que se realizó la transacción.
     * @throws CantidadException Si el monto no es válido (por ejemplo, cero o negativo cuando no corresponde).
     */
    public Transaccion(int id, int idUsuario, int idCuenta, double monto, String tipo,
                       String categoria, String descripcion, LocalDate fecha) throws CantidadException {
        setId(id);
        setIdUsuario(idUsuario);
        setMonto(monto);
        setTipo(tipo);
        setCategoria(categoria);
        setDescripcion(descripcion);
        setFecha(fecha);
        setIdCuenta(idCuenta);
    }

    /**
     * Constructor de una transacción sin especificar el ID (por defecto se establece en 0).
     *
     * @param idUsuario ID del usuario al que pertenece la transacción.
     * @param monto Monto de la transacción.
     * @param tipo Tipo de transacción.
     * @param categoria Categoría de la transacción.
     * @param descripcion Descripción de la transacción.
     * @param fecha Fecha en que se realizó.
     * @throws CantidadException Si el monto no es válido.
     */
    public Transaccion(int idUsuario, int idCuenta, double monto, String tipo,
                       String categoria, String descripcion, LocalDate fecha) throws CantidadException {
        this(0, idUsuario, idCuenta, monto, tipo, categoria, descripcion, fecha);
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

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
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
        if(monto == 0) throw  new CantidadException("El monto debe ser diferente a 0.");
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
