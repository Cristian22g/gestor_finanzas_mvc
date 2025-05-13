package com.finanzas.gestor_finanzas.servicio;


import com.finanzas.gestor_finanzas.excepciones.*;
import com.finanzas.gestor_finanzas.interfaces.IUsuarioServicio;
import com.finanzas.gestor_finanzas.modelo.Usuario;
import com.finanzas.gestor_finanzas.repositorio.UsuarioRepositorio;

import java.util.List;

public class UsuarioServicio implements IUsuarioServicio {
    private UsuarioRepositorio repositorio;

    public UsuarioServicio() {
        this.repositorio = new UsuarioRepositorio();
    }

    @Override
    public List<Usuario> obtenerTodos()
            throws ContrasenaException, DniException, NombreUsuarioException, ApellidoException, NombreException {

        return repositorio.obtenerUsuarios();
    }

    @Override
    public Usuario obtenerPorId(int id)
            throws ContrasenaException, DniException, NombreUsuarioException, ApellidoException, NombreException {

        List<Usuario> usuarios = repositorio.obtenerUsuarios();

        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Usuario registrarUsuario(String nombre_usuario, String contrasena,String dni, String nombre, String primerApellido, String segundoApellido)
            throws NombreUsuarioException, ContrasenaException, DniException, ApellidoException, NombreException {

        Usuario usuario = new Usuario(nombre_usuario,contrasena,dni,nombre,primerApellido,segundoApellido);
        List<Usuario> usuarios = repositorio.obtenerUsuarios();

        if (usuarios.contains(usuario)) throw new DniException("Este DNI ya está en uso.");

        if(usuarios.stream()
                .anyMatch(u -> u.getNombreUsuario().equals(nombre_usuario)))
            throw new NombreUsuarioException("Este nombre de usuario ya está en uso.");

        return repositorio.registrarUsuario(usuario);
    }

    @Override
    public boolean eliminarUsuario(int id)
            throws ContrasenaException, DniException, NombreUsuarioException, ApellidoException, NombreException{

        Usuario usuario = obtenerPorId(id);
        if(usuario == null) return false;

        return repositorio.eliminarUsuario(id);
    }
}
