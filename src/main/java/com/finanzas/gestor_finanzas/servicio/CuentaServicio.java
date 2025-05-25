package com.finanzas.gestor_finanzas.servicio;

import java.util.List;

import com.finanzas.gestor_finanzas.excepciones.*;
import com.finanzas.gestor_finanzas.interfaces.ICuentaServicio;
import com.finanzas.gestor_finanzas.modelo.Cuenta;
import com.finanzas.gestor_finanzas.repositorio.CuentaRepositorio;

/**
 * Implementación del servicio de gestión de cuentas.
 */
public class CuentaServicio implements ICuentaServicio{
    
	private CuentaRepositorio repositorio;

	/**
	 * Constructor con una instancia de {@Code CuentaRepositorio}.
	 */
    public CuentaServicio() {
        this.repositorio = new CuentaRepositorio();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Cuenta> obtenerCuentas() throws NombreCuentaException {
		 	return repositorio.obtenerCuentas();
		}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cuenta obtenerPorId(int id) throws NombreCuentaException {
        return repositorio.obtenerCuentas().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cuenta registrarCuenta(int idUsuario, String nombreCuenta, double saldoActual) throws NombreCuentaException, CantidadException {
		Cuenta cuenta = new Cuenta(idUsuario, nombreCuenta, saldoActual);
		List<Cuenta> cuentas = repositorio.obtenerCuentas();
		
		Cuenta disponible = cuentas.stream()
								.filter(c -> c.getIdUsuario() == cuenta.getIdUsuario() && c.getNombreCuenta().equals(cuenta.getNombreCuenta()))
								.findFirst()
								.orElse(null);
		
		if(disponible != null) return null;
		
		return repositorio.registrarCuenta(cuenta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean eliminarCuenta(int id) {
		return repositorio.eliminarCuenta(id);
	}

	public boolean cambiarSaldo(int id_cuenta, double nuevoSaldo){
		return repositorio.cambioSaldo(id_cuenta, nuevoSaldo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Cuenta> obtenerPorUsuarioId(int idUsuario) throws NombreCuentaException {
		return repositorio.obtenerCuentasPorUsuarioId(idUsuario);
	}

}
