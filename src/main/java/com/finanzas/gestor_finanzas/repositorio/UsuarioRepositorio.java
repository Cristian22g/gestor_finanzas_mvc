package com.finanzas.gestor_finanzas.repositorio;


import com.finanzas.gestor_finanzas.dao.DBConnection;
import com.finanzas.gestor_finanzas.excepciones.*;
import com.finanzas.gestor_finanzas.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Repositorio encargado de gestionar el acceso a datos relacionados con usuarios.
 */
public class UsuarioRepositorio {

    public UsuarioRepositorio(){}


    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return Lista de usuarios.
     * @throws ContrasenaException Si la nueva contraseña no cumple con los requisitos.
     * @throws DniException Si es Dni es inválido.
     * @throws @NombreUsuarioException Si el nuevo nombre de usuario no cumple con los requisitos.
     * @throws @NombreApellidoException Si el nuevo nombre y apellido no cumplen con los requisitos.
     */
    public List<Usuario> obtenerUsuarios() throws ContrasenaException, DniException, NombreUsuarioException, NombreApellidoException {
        String sql = "SELECT id, nombre_usuario, contrasena, dni, nombre, primer_apellido, segundo_apellido FROM USUARIOS";

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ) {

                List<Usuario> usuarios = new ArrayList<Usuario>();

                while (rs.next()){
                    usuarios.add(mapearUsuarios(rs));
                }
                return usuarios;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }


    /**
     * Registra un nuevo usuario.
     *
     * @param usuario Objeto {@code Usuario} a registrar.
     * @return El usuario registrado.
     */
    public Usuario registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena, dni, nombre, primer_apellido, segundo_apellido) VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getDni());
            ps.setString(4, usuario.getNombre());
            ps.setString(5, usuario.getPrimerApellido());
            ps.setString(6, usuario.getSegundoApellido());

            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas == 0) return null;

            return usuario;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Elimina un usuario según su ID.
     *
     * @param id ID del usuario a eliminar.
     * @return {@code true} si fue eliminado correctamente, {@code false} si no se encontró.
     */
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Devuelve un objeto {@code Usuario} a partir de un objeto {@code ResultSet}.
     *
     * @return Usuario
     * @param rs ResultSet.
     * @throws ContrasenaException Si la nueva contraseña no cumple con los requisitos.
     * @throws DniException Si es Dni es inválido.
     * @throws @NombreUsuarioException Si el nuevo nombre de usuario no cumple con los requisitos.
     * @throws @NombreApellidoException Si el nuevo nombre y apellido no cumplen con los requisitos.
     */
    private Usuario mapearUsuarios(ResultSet rs) throws NombreUsuarioException, ContrasenaException, DniException, SQLException, NombreApellidoException{
    	int id = rs.getInt("id");
        String nombreUsuario = rs.getString("nombre_usuario");
        String contrasena = rs.getString("contrasena");
        String dni = rs.getString("dni");
        String nombre = rs.getString("nombre");
        String primerApellido = rs.getString("primer_apellido");
        String segundoApellido = rs.getString("segundo_apellido");

        Usuario us = new Usuario(id,nombreUsuario,contrasena,dni,nombre,primerApellido,segundoApellido);
        return us;
    }
}
