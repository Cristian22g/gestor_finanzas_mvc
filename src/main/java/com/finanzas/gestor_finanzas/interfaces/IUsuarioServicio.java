package com.finanzas.gestor_finanzas.interfaces;


import com.finanzas.gestor_finanzas.excepciones.*;
import com.finanzas.gestor_finanzas.modelo.Usuario;

import java.util.List;

/**
 * Interfaz que define las operaciones relacionadas con la gestión de usuarios.
 */
public interface IUsuarioServicio {

    /**
     * Obtiene la lista de todos los usuarios registrados.
     *
     * @return Lista de objetos {@code Usuario}.
     * @throws ContrasenaException Si la nueva contraseña no cumple con los requisitos.
     * @throws DniException Si es Dni es inválido.
     * @throws @NombreUsuarioException Si el nuevo nombre de usuario no cumple con los requisitos.
     * @throws @NombreApellidoException Si el nuevo nombre y apellido no cumplen con los requisitos.
     */
    List<Usuario> obtenerTodos()  throws ContrasenaException, DniException, NombreUsuarioException, NombreApellidoException;

    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id Identificador único del usuario.
     * @return El usuario correspondiente, o {@code null} si no existe.
     * @throws ContrasenaException Si la nueva contraseña no cumple con los requisitos.
     * @throws DniException Si es Dni es inválido.
     * @throws @NombreUsuarioException Si el nuevo nombre de usuario no cumple con los requisitos.
     * @throws @NombreApellidoException Si el nuevo nombre y apellido no cumplen con los requisitos.
     */
    Usuario obtenerPorId(int id) throws ContrasenaException, DniException, NombreUsuarioException, NombreApellidoException;

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param nombreUsuario Nombre de usuario único.
     * @param contrasena Contraseña del usuario.
     * @param dni Documento nacional de identidad.
     * @param nombre Nombre del usuario.
     * @param primerApellido Primer apellido.
     * @param segundoApellido Segundo apellido.
     * @return El usuario registrado.
     * @throws ContrasenaException Si la nueva contraseña no cumple con los requisitos.
     * @throws DniException Si es Dni es inválido.
     * @throws @NombreUsuarioException Si el nuevo nombre de usuario no cumple con los requisitos.
     * @throws @NombreApellidoException Si el nuevo nombre y apellido no cumplen con los requisitos.
     */
    Usuario registrarUsuario(String nombreUsuario, String contrasena,String dni, String nombre, String primerApellido, String segundoApellido) throws NombreUsuarioException, ContrasenaException, DniException, NombreApellidoException;

    /**
     * Elimina un usuario existente.
     *
     * @param id Identificador del usuario a eliminar.
     * @return {@code true} si se eliminó correctamente; {@code false} en caso contrario.
     * @throws ContrasenaException Si la nueva contraseña no cumple con los requisitos.
     * @throws DniException Si es Dni es inválido.
     * @throws @NombreUsuarioException Si el nuevo nombre de usuario no cumple con los requisitos.
     * @throws @NombreApellidoException Si el nuevo nombre y apellido no cumplen con los requisitos.
     */
    boolean eliminarUsuario(int id) throws ContrasenaException, DniException, NombreUsuarioException, NombreApellidoException;
}
