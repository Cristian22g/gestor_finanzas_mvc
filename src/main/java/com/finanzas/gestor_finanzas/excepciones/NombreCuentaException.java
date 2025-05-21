package com.finanzas.gestor_finanzas.excepciones;

/**
 * Excepción lanzada cuando el nombre de una cuenta no es válido (por ejemplo, vacío o duplicado).
 */
public class NombreCuentaException extends Exception{

	/**
	 * Crea una nueva instancia de NombreCuentaException con un mensaje descriptivo.
	 *
	 * @param msg Detalles del problema con el nombre de la cuenta.
	 */
	public NombreCuentaException(String msg){
		super(msg);
	}

}
