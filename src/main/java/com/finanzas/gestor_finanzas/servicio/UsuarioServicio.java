package com.finanzas.gestor_finanzas.servicio;


import com.finanzas.gestor_finanzas.excepciones.*;
import com.finanzas.gestor_finanzas.interfaces.IUsuarioServicio;
import com.finanzas.gestor_finanzas.modelo.Usuario;
import com.finanzas.gestor_finanzas.repositorio.UsuarioRepositorio;

import java.util.List;

/**
 * Implementación del servicio de gestión de usuarios.
 */
public class UsuarioServicio implements IUsuarioServicio {
    private UsuarioRepositorio repositorio;

    /**
     * Constructor con una instancia de {@code UsuarioRepositorio}.
     */
    public UsuarioServicio() {
        this.repositorio = new UsuarioRepositorio();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Usuario> obtenerTodos()
            throws ContrasenaException, DniException, NombreUsuarioException, NombreApellidoException {

        return repositorio.obtenerUsuarios();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario obtenerPorId(int id)
            throws ContrasenaException, DniException, NombreUsuarioException, NombreApellidoException {

        List<Usuario> usuarios = repositorio.obtenerUsuarios();

        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario registrarUsuario(String nombreUsuario, String contrasena,String dni, String nombre, String primerApellido, String segundoApellido)
            throws NombreUsuarioException, ContrasenaException, DniException, NombreApellidoException {

        Usuario usuario = new Usuario(nombreUsuario,contrasena,dni,nombre,primerApellido,segundoApellido);
        List<Usuario> usuarios = repositorio.obtenerUsuarios();

        if (usuarios.contains(usuario)) throw new DniException("Este DNI ya está en uso.");

        if(usuarios.stream()
                .anyMatch(u -> u.getNombreUsuario().equals(nombreUsuario)))
            throw new NombreUsuarioException("Este nombre de usuario ya está en uso.");

        return repositorio.registrarUsuario(usuario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean eliminarUsuario(int id)
            throws ContrasenaException, DniException, NombreUsuarioException, NombreApellidoException{

        Usuario usuario = obtenerPorId(id);
        if(usuario == null) return false;

        return repositorio.eliminarUsuario(id);
    }
}
