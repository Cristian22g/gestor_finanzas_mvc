package com.finanzas.gestor_finanzas.excepciones;

/**
 * Excepción lanzada cuando la contraseña no cumple con los criterios de validación.
 */
public class ContrasenaException extends Exception{

    /**
     * Crea una nueva instancia de ContrasenaException con un mensaje descriptivo.
     *
     * @param msg Mensaje que explica por qué la contraseña es inválida.
     */
    public ContrasenaException(String msg){
        super(msg);
    }
}
