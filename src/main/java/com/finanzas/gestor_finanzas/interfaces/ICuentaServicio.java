package com.finanzas.gestor_finanzas.interfaces;

import com.finanzas.gestor_finanzas.excepciones.CantidadException;
import com.finanzas.gestor_finanzas.excepciones.NombreCuentaException;
import com.finanzas.gestor_finanzas.modelo.Cuenta;

import java.util.List;




public interface ICuentaServicio {
    List<Cuenta> obtenerCuentas()throws NombreCuentaException;
    Cuenta obtenerPorId(int id)throws NombreCuentaException;
    Cuenta registrarCuenta(int idUsuario, String nombreCuenta, double saldoActual)throws NombreCuentaException, CantidadException;
    boolean eliminarCuenta(int id);
    List<Cuenta> obtenerPorUsuarioId(int idUsuario)throws NombreCuentaException;
}
