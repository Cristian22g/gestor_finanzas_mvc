package com.finanzas.gestor_finanzas.excepciones;

/**
 * Excepción lanzada cuando uno o más campos obligatorios están vacíos.
 */
public class CampoVacioException extends Exception{

    /**
     * Crea una nueva instancia de CampoVacioException con un mensaje descriptivo.
     *
     * @param msg Mensaje que describe el error.
     */
    public CampoVacioException(String msg){
        super(msg);
    }
}
