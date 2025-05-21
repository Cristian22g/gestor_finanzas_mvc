package com.finanzas.gestor_finanzas.excepciones;

/**
 * Excepción lanzada cuando se introduce una cantidad inválida (por ejemplo, negativa o cero cuando no corresponde).
 */
public class CantidadException extends Exception {

    /**
     * Crea una nueva instancia de CantidadException con un mensaje descriptivo.
     *
     * @param msg Descripción del error relacionado con la cantidad.
     */
    public CantidadException(String msg) {
        super(msg);
    }
}
