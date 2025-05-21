package com.finanzas.gestor_finanzas.excepciones;

/**
 * Excepción lanzada cuando el nombre de usuario no es válido o ya está en uso.
 */
public class NombreUsuarioException extends Exception{

    /**
     * Crea una nueva instancia de NombreUsuarioException con un mensaje descriptivo.
     *
     * @param msg Mensaje explicativo del problema con el nombre de usuario.
     */
    public NombreUsuarioException(String msg){
        super(msg);
    }
}
