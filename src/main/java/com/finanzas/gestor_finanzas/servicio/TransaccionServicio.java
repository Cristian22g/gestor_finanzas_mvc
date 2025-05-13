package com.finanzas.gestor_finanzas.servicio;


import com.finanzas.gestor_finanzas.interfaces.ITransaccionServicio;
import com.finanzas.gestor_finanzas.modelo.Transaccion;
import com.finanzas.gestor_finanzas.repositorio.TransaccionRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TransaccionServicio  implements ITransaccionServicio {
    private final TransaccionRepositorio repositorio;

    public TransaccionServicio() {
        this.repositorio = new TransaccionRepositorio();
    }

    @Override
    public List<Transaccion> obtenerTransacciones() throws Exception {
        return repositorio.obtenerTransacciones();
    }

    @Override
    public List<Transaccion> obtenerPorUsuario(int idUsuario) throws Exception {
        return repositorio.obtenerTransaccionesPorUsuario(idUsuario);
    }

    @Override
    public Transaccion registrarTransaccion(int idUsuario, double monto, String tipo, String categoria, String descripcion, LocalDate fecha) throws Exception {
        Transaccion transaccion = new Transaccion(idUsuario, monto, tipo, categoria, descripcion, fecha);
        return repositorio.registrarTransaccion(transaccion);
    }

    @Override
    public boolean eliminarTransaccion(int id) throws Exception {
        List<Transaccion> transacciones = obtenerTransacciones();
        Transaccion transaccion = transacciones.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);

        if(transaccion == null) return false;
        return repositorio.eliminarTransaccion(id);
    }

    @Override
    public List<Transaccion> filtrarPorCategoriaUsuario(int idUsuario, String categoria) throws Exception {
        return repositorio.obtenerPorCategoriaUsuario(idUsuario, categoria);
    }

    @Override
    public List<Transaccion> filtrarPorFechaUsuario(int idUsuario, LocalDate desde, LocalDate hasta) throws Exception {
        return repositorio.obtenerPorFechaUsuario(idUsuario, desde, hasta);
    }

	@Override
	public List<Transaccion> filtrarPorCategoria(String categoria) throws Exception {
		return repositorio.obtenerPorCategoria(categoria);
	}

	@Override
	public List<Transaccion> filtrarPorFecha(LocalDate desde, LocalDate hasta) throws Exception {
		return repositorio.obtenerPorFecha(desde, hasta);
	}
}
