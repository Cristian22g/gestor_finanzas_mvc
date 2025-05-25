package com.finanzas.gestor_finanzas.servicio;


import com.finanzas.gestor_finanzas.excepciones.CantidadException;
import com.finanzas.gestor_finanzas.interfaces.ITransaccionServicio;
import com.finanzas.gestor_finanzas.modelo.Transaccion;
import com.finanzas.gestor_finanzas.repositorio.TransaccionRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Implementación del servicio de gestión de transacciones.
 */
public class TransaccionServicio  implements ITransaccionServicio {
    private final TransaccionRepositorio repositorio;

    /**
     * Constructor con una instancia de {@code TransaccionRepositorio}.
     */
    public TransaccionServicio() {
        this.repositorio = new TransaccionRepositorio();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Transaccion> obtenerTransacciones(){
        return repositorio.obtenerTransacciones();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Transaccion> obtenerPorUsuario(int idUsuario){
        return repositorio.obtenerTransaccionesPorUsuario(idUsuario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Transaccion registrarTransaccion(int idUsuario, int idCuenta, double monto, String tipo, String categoria, String descripcion, LocalDate fecha) throws CantidadException {
        Transaccion transaccion = new Transaccion(idUsuario, idCuenta, monto, tipo, categoria, descripcion, fecha);
        return repositorio.registrarTransaccion(transaccion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean eliminarTransaccion(int id){
        List<Transaccion> transacciones = obtenerTransacciones();
        Transaccion transaccion = transacciones.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);

        if(transaccion == null) return false;
        return repositorio.eliminarTransaccion(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Transaccion> filtrarPorCategoriaUsuario(int idUsuario, String categoria){
        return repositorio.obtenerPorCategoriaUsuario(idUsuario, categoria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Transaccion> filtrarPorFechaUsuario(int idUsuario, LocalDate desde, LocalDate hasta){
        return repositorio.obtenerPorFechaUsuario(idUsuario, desde, hasta);
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public List<Transaccion> filtrarPorCategoria(String categoria){
		return repositorio.obtenerPorCategoria(categoria);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public List<Transaccion> filtrarPorFecha(LocalDate desde, LocalDate hasta){
		return repositorio.obtenerPorFecha(desde, hasta);
	}

    public List<Transaccion> filtrarPorCuentaUsuario(int idUsuario, int idCuenta){
        return repositorio.filtrarPorCuentaUsuario(idUsuario, idCuenta);
    }
}
