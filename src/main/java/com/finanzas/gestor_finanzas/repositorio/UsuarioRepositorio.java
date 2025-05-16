package com.finanzas.gestor_finanzas.repositorio;


import com.finanzas.gestor_finanzas.dao.DBConnection;
import com.finanzas.gestor_finanzas.excepciones.*;
import com.finanzas.gestor_finanzas.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UsuarioRepositorio {

    public UsuarioRepositorio(){}

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
