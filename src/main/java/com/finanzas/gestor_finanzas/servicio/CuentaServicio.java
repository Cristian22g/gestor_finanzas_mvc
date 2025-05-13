package com.finanzas.gestor_finanzas.servicio;

import java.util.List;

import com.finanzas.gestor_finanzas.excepciones.*;
import com.finanzas.gestor_finanzas.interfaces.ICuentaServicio;
import com.finanzas.gestor_finanzas.modelo.Cuenta;
import com.finanzas.gestor_finanzas.repositorio.CuentaRepositorio;


public class CuentaServicio implements ICuentaServicio{
    
	private CuentaRepositorio repositorio;

    public CuentaServicio() {
        this.repositorio = new CuentaRepositorio();
    }
    
	@Override
	public List<Cuenta> obtenerCuentas() throws NombreCuentaException {
		 	return repositorio.obtenerCuentas();
		}

	@Override
	public Cuenta obtenerPorId(int id) throws NombreCuentaException {
        return repositorio.obtenerCuentas().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
	}

	@Override
	public Cuenta registrarCuenta(Cuenta cuenta) throws NombreCuentaException {
		List<Cuenta> cuentas = repositorio.obtenerCuentas();
		
		Cuenta disponible = cuentas.stream()
								.filter(c -> c.getIdUsuario() == cuenta.getIdUsuario() && c.getNombreCuenta().equals(cuenta.getNombreCuenta()))
								.findFirst()
								.orElse(null);
		
		if(disponible != null) return null;
		
		return repositorio.registrarCuenta(cuenta);
	}

	@Override
	public boolean eliminarCuenta(int id) {
		return repositorio.eliminarCuenta(id);
	}

	@Override
	public List<Cuenta> obtenerPorUsuarioId(int idUsuario) throws NombreCuentaException {
		return repositorio.obtenerPorUsuarioId(idUsuario);
	}

}
