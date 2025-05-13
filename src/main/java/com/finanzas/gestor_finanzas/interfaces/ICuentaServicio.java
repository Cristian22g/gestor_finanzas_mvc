package com.finanzas.gestor_finanzas.interfaces;

import com.finanzas.gestor_finanzas.excepciones.NombreCuentaException;
import com.finanzas.gestor_finanzas.modelo.Cuenta;

import java.util.List;




public interface ICuentaServicio {
    List<Cuenta> obtenerCuentas()throws NombreCuentaException;
    Cuenta obtenerPorId(int id)throws NombreCuentaException;
    Cuenta registrarCuenta(Cuenta cuenta)throws NombreCuentaException;
    boolean eliminarCuenta(int id);
    List<Cuenta> obtenerPorUsuarioId(int idUsuario)throws NombreCuentaException;
}
