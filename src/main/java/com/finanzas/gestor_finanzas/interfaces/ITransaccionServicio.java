package com.finanzas.gestor_finanzas.interfaces;


import com.finanzas.gestor_finanzas.excepciones.CantidadException;
import com.finanzas.gestor_finanzas.modelo.Transaccion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz que define las operaciones relacionadas con la gestión de transacciones financieras.
 */
public interface ITransaccionServicio {

    /**
     * Obtiene todas las transacciones registradas.
     *
     * @return Lista de transacciones.
     */
    List<Transaccion> obtenerTransacciones();

    /**
     * Obtiene las transacciones asociadas a un usuario específico.
     *
     * @param idUsuario Identificador del usuario.
     * @return Lista de transacciones del usuario.
     */
    List<Transaccion> obtenerPorUsuario(int idUsuario);

    /**
     * Registra una nueva transacción en el sistema.
     *
     * @param idUsuario Identificador del usuario asociado.
     * @param monto Monto de la transacción.
     * @param tipo Tipo de transacción (ingreso, gasto, etc.).
     * @param categoria Categoría financiera.
     * @param descripcion Descripción de la transacción.
     * @param fecha Fecha de la transacción.
     * @return La transacción registrada.
     * @throws CantidadException Si la cantidad no es válida.
     */
    Transaccion registrarTransaccion(int idUsuario, int idCuenta, double monto, String tipo, String categoria, String descripcion, LocalDate fecha) throws CantidadException;

    /**
     * Elimina una transacción por su ID.
     *
     * @param id Identificador de la transacción.
     * @return {@code true} si fue eliminada correctamente, {@code false} en caso contrario.
     */
    boolean eliminarTransaccion(int id);

    /**
     * Filtra las transacciones por categoría de un usuario específico.
     *
     * @param idUsuario Identificador del usuario.
     * @param categoria Categoría a filtrar.
     * @return Lista de transacciones filtradas.
     */
    List<Transaccion> filtrarPorCategoriaUsuario(int idUsuario, String categoria);

    /**
     * Filtra las transacciones por un rango de fechas y usuario.
     *
     * @param idUsuario Identificador del usuario.
     * @param desde Fecha inicial.
     * @param hasta Fecha final.
     * @return Lista de transacciones dentro del rango.
     */
    List<Transaccion> filtrarPorFechaUsuario(int idUsuario, LocalDate desde, LocalDate hasta);

    /**
     * Filtra todas las transacciones por categoría.
     *
     * @param categoria Categoría a filtrar.
     * @return Lista de transacciones que pertenecen a la categoría.
     */
    List<Transaccion> filtrarPorCategoria(String categoria);

    /**
     * Filtra todas las transacciones por un rango de fechas.
     *
     * @param desde Fecha inicial.
     * @param hasta Fecha final.
     * @return Lista de transacciones en el rango de fechas dado.
     */
    List<Transaccion> filtrarPorFecha(LocalDate desde, LocalDate hasta);
}
