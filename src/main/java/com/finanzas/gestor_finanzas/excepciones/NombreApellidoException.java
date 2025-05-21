package com.finanzas.gestor_finanzas.excepciones;

/**
 * Excepción lanzada cuando el nombre o los apellidos proporcionados no son válidos.
 */
public class NombreApellidoException extends Exception{

	/**
	 * Crea una nueva instancia de NombreApellidoException con un mensaje descriptivo.
	 *
	 * @param msg Explicación del error en nombre o apellidos.
	 */
	public NombreApellidoException(String msg) {
		super(msg);
	}
}
