package com.finanzas.gestor_finanzas.excepciones;

/**
 * Excepción lanzada cuando el DNI proporcionado no es válido o tiene un formato incorrecto.
 */
public class DniException extends Exception{

    /**
     * Crea una nueva instancia de DniException con un mensaje descriptivo.
     *
     * @param msg Descripción del error relacionado con el DNI.
     */
    public DniException(String msg){
        super(msg);
    }
}
