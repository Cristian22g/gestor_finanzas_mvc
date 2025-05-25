package com.finanzas.gestor_finanzas.validaciones;

import com.finanzas.gestor_finanzas.excepciones.NombreApellidoException;


/**
 * Clase de utilidades que proporciona métodos estáticos para validar datos de entrada.
 */
public class Validaciones {

    /**
     * Valida si un DNI español es correcto en formato y letra de control.
     *
     * @param dni DNI a validar (8 dígitos seguidos de una letra).
     * @return {@code true} si el DNI es válido, {@code false} en caso contrario.
     */
    public static boolean ValidarDni(String dni){
        if (dni == null || !dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
            return false;
        }

        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letraEsperada = letras.charAt(numero % 23);
        char letraActual = dni.charAt(8);

        return letraEsperada == letraActual;
    }

    /**
     * Valida si el nombre de usuario tiene un formato correcto.
     * Debe tener entre 4 y 20 caracteres alfanuméricos sin símbolos ni espacios.
     *
     * @param nombreUsuario Nombre de usuario a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    public static boolean validarNombreUsuario(String nombreUsuario){

                return nombreUsuario.matches("^[a-zA-Z0-9]{4,20}$");
    }

    /**
     * Valida si una contraseña cumple con los requisitos mínimos de seguridad.
     * Debe tener al menos 6 caracteres, una mayúscula, una minúscula, un número y un carácter especial.
     *
     * @param contrasena Contraseña a validar.
     * @return {@code true} si es segura y válida, {@code false} en caso contrario.
     */
    public static boolean validarContrasena(String contrasena){
        //debe contener mínimo 6 caracteres y números, letras mayúscula y minúscula, y caracter especial.
        return contrasena.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).{6,}$");
    }

    /**
     * Valida si el nombre de una cuenta es válido.
     * Debe tener entre 4 y 20 caracteres alfanuméricos y puede contener espacios.
     *
     * @param nombreCuenta Nombre de la cuenta a validar.
     * @return {@code true} si el nombre es válido, {@code false} en caso contrario.
     */
    public static boolean validarNombreCuenta(String nombreCuenta){
    	return nombreCuenta.matches("^[a-zA-ZÁÉÍÓÚáéíóúÑñü ]{4,20}$");
    }

    /**
     * Valida si un nombre o apellido es válido.
     * Debe tener entre 2 y 20 letras, y puede contener tildes.
     *
     * @param nombre Nombre o apellido a validar.
     * @return {@code true} si el texto es válido, {@code false} en caso contrario.
     */
    public static boolean validarNombreOapellido(String nombre){
        if (nombre == null || nombre.isEmpty()) {
            return false;
        }
    	return nombre.matches("^[a-zA-ZÁÉÍÓÚáéíóúÑñü]{2,20}$");
    }

}
