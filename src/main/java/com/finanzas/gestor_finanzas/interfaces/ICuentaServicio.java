package com.finanzas.gestor_finanzas.interfaces;

import com.finanzas.gestor_finanzas.excepciones.CantidadException;
import com.finanzas.gestor_finanzas.excepciones.NombreCuentaException;
import com.finanzas.gestor_finanzas.modelo.Cuenta;

import java.util.List;




public interface ICuentaServicio {

    /**
     * Interfaz que define las operaciones relacionadas con la gestión de cuentas financieras.
     */

    /**
     * Obtiene la lista de todas las cuentas registradas.
     *
     * @return Lista de cuentas.
     *
     * @throws NombreCuentaException Si el nombre de la cuenta no cumple con los requisitos o el usuario ya tiene una cuenta con ese nombre.
     *
     */
    List<Cuenta> obtenerCuentas()throws NombreCuentaException;

    /**
     * Obtiene una cuenta por su ID.
     *
     * @param id Identificador único de la cuenta.
     * @return La cuenta correspondiente, o {@code null} si no se encuentra.
     * @throws NombreCuentaException Si el nombre de la cuenta no cumple con los requisitos o el usuario ya tiene una cuenta con ese nombre.
     */
    Cuenta obtenerPorId(int id)throws NombreCuentaException;

    /**
     * Registra una nueva cuenta.
     *
     * @param idUsuario ID del usuario propietario.
     * @param nombreCuenta Nombre de la cuenta.
     * @param saldoActual Saldo inicial o actual.
     * @return La cuenta registrada.
     * @throws NombreCuentaException Si el nombre de la cuenta no cumple con los requisitos o el usuario ya tiene una cuenta con ese nombre.
     * @throws CantidadException Si la cantidad inicial es negativa.
     */
    Cuenta registrarCuenta(int idUsuario, String nombreCuenta, double saldoActual)throws NombreCuentaException, CantidadException;

    /**
     * Elimina una cuenta existente.
     *
     * @param id Identificador de la cuenta a eliminar.
     * @return {@code true} si la cuenta fue eliminada; {@code false} en caso contrario.
     */
    boolean eliminarCuenta(int id);

    /**
     * Obtiene las cuentas asociadas a un usuario.
     *
     * @param idUsuario ID del usuario.
     * @return Lista de cuentas del usuario.
     * @throws NombreCuentaException Si el nombre de la cuenta no cumple con los requisitos o el usuario ya tiene una cuenta con ese nombre.
     */
    List<Cuenta> obtenerPorUsuarioId(int idUsuario)throws NombreCuentaException;
}
