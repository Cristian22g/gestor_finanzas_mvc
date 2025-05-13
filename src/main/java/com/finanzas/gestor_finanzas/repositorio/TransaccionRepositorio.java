package com.finanzas.gestor_finanzas.repositorio;



import com.finanzas.gestor_finanzas.dao.DBConnection;
import com.finanzas.gestor_finanzas.modelo.Transaccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransaccionRepositorio {

    public TransaccionRepositorio(){}

    public List<Transaccion> obtenerTransacciones(){
        String sql = "SELECT * FROM TRANSACCIONES";

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {

            List<Transaccion> transacciones = new ArrayList<Transaccion>();

            while (rs.next()){
                transacciones.add(mapearTransaccion(rs));
            }
            return transacciones;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<Transaccion> obtenerTransaccionesPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM transacciones WHERE id_usuario = ?";
        List<Transaccion> transacciones = new ArrayList<>();

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, idUsuario);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    transacciones.add(mapearTransaccion(rs));
                }
            }
            return transacciones;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }

    }

    public Transaccion registrarTransaccion(Transaccion transaccion) {
        String sql = "INSERT INTO transacciones (id_usuario, monto, tipo, categoria, descripcion, fecha) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, transaccion.getIdUsuario());
            ps.setDouble(2, transaccion.getMonto());
            ps.setString(3, transaccion.getTipo());
            ps.setString(4, transaccion.getCategoria());
            ps.setString(5, transaccion.getDescripcion());
            ps.setDate(6, java.sql.Date.valueOf(transaccion.getFecha()));

            int filas = ps.executeUpdate();
            if (filas == 0) return null;

            return transaccion;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Transaccion> obtenerPorCategoriaUsuario(int idUsuario, String categoria) {
        String sql = "SELECT * FROM transacciones WHERE id_usuario = ? AND categoria = ?";
        List<Transaccion> transacciones = new ArrayList<>();

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, idUsuario);
            ps.setString(2, categoria);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    transacciones.add(mapearTransaccion(rs));
                }
            }
            return transacciones;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }
    

	public List<Transaccion> obtenerPorCategoria(String categoria) {
        String sql = "SELECT * FROM transacciones WHERE categoria = ?";
        List<Transaccion> transacciones = new ArrayList<>();

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, categoria);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    transacciones.add(mapearTransaccion(rs));
                }
            }
            return transacciones;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
	}

    public List<Transaccion> obtenerPorFechaUsuario(int idUsuario, LocalDate fechaInicio, LocalDate fechaFin) {
        String sql = "SELECT * FROM transacciones WHERE id_usuario = ? AND fecha BETWEEN ? AND ?";
        List<Transaccion> transacciones = new ArrayList<>();

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, idUsuario);
            ps.setDate(2, java.sql.Date.valueOf(fechaInicio));
            ps.setDate(3, java.sql.Date.valueOf(fechaFin));

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    transacciones.add(mapearTransaccion(rs));
                }
            }
            return transacciones;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }
    

	public List<Transaccion> obtenerPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
		 String sql = "SELECT * FROM transacciones WHERE fecha BETWEEN ? AND ?";
	        List<Transaccion> transacciones = new ArrayList<>();

	        try (
	                Connection conn = DBConnection.getConnection();
	                PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
	            ps.setDate(1, java.sql.Date.valueOf(fechaInicio));
	            ps.setDate(2, java.sql.Date.valueOf(fechaFin));

	            try(ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    transacciones.add(mapearTransaccion(rs));
	                }
	            }
	            return transacciones;
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return Collections.emptyList();
	        }

	}

    public boolean eliminarTransaccion(int id) {
        String sql = "DELETE FROM transacciones WHERE id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Transaccion mapearTransaccion(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idUsuario = rs.getInt("id_usuario");
        double monto = rs.getDouble("monto");
        String tipo = rs.getString("tipo");
        String categoria = rs.getString("categoria");
        String descripcion = rs.getString("descripcion");
        LocalDate fecha = rs.getDate("fecha").toLocalDate();

        return new Transaccion(id, idUsuario, monto, tipo, categoria, descripcion, fecha);
    }


}
