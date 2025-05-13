package com.finanzas.gestor_finanzas.interfaces;


import com.finanzas.gestor_finanzas.excepciones.*;
import com.finanzas.gestor_finanzas.modelo.Usuario;

import java.util.List;

public interface IUsuarioServicio {
    Usuario obtenerPorId(int id) throws ContrasenaException, DniException, NombreUsuarioException, ApellidoException, NombreException;
    List<Usuario> obtenerTodos()  throws ContrasenaException, DniException, NombreUsuarioException, ApellidoException, NombreException;
    Usuario registrarUsuario(String nombre_usuario, String contrasena,String dni, String nombre, String primerApellido, String segundoApellido) throws NombreUsuarioException, ContrasenaException, DniException, ApellidoException, NombreException;
    boolean eliminarUsuario(int id) throws ContrasenaException, DniException, NombreUsuarioException, ApellidoException, NombreException;
}
