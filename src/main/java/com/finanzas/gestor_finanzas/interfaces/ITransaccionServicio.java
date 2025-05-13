package com.finanzas.gestor_finanzas.interfaces;


import com.finanzas.gestor_finanzas.modelo.Transaccion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ITransaccionServicio {
    List<Transaccion> obtenerTransacciones() throws Exception;
    List<Transaccion> obtenerPorUsuario(int idUsuario) throws Exception;
    Transaccion registrarTransaccion(int idUsuario, double monto, String tipo, String categoria, String descripcion, LocalDate fecha) throws Exception;
    boolean eliminarTransaccion(int id) throws Exception;
    List<Transaccion> filtrarPorCategoriaUsuario(int idUsuario, String categoria) throws Exception;
    List<Transaccion> filtrarPorFechaUsuario(int idUsuario, LocalDate desde, LocalDate hasta) throws Exception;
    List<Transaccion> filtrarPorCategoria(String categoria) throws Exception;
    List<Transaccion> filtrarPorFecha(LocalDate desde, LocalDate hasta) throws Exception;
}
