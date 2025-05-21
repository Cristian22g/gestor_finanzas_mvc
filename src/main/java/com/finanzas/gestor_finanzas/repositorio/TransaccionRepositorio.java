package com.finanzas.gestor_finanzas.repositorio;



import com.finanzas.gestor_finanzas.dao.DBConnection;
import com.finanzas.gestor_finanzas.excepciones.CantidadException;
import com.finanzas.gestor_finanzas.modelo.Transaccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repositorio encargado del acceso a los datos relacionados con las transacciones financieras.
 */
public class TransaccionRepositorio {

    public TransaccionRepositorio(){}

    /**
     * Repositorio encargado del acceso a los datos relacionados con las transacciones financieras.
     */
    public List<Transaccion> obtenerTransacciones(){
        String sql = "SELECT * FROM TRANSACCIONES";

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {

            List<Transaccion> transacciones = new ArrayList<Transaccion>();

            while (rs.next()) {
                transacciones.add(mapearTransaccion(rs));
            }
            return  transacciones;
        } catch (SQLException | CantidadException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Obtiene las transacciones de un usuario específico.
     *
     * @param idUsuario ID del usuario.
     * @return Lista de transacciones del usuario.
     */
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

        } catch (SQLException | CantidadException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }

    }

    /**
     * Registra una nueva transacción.
     *
     * @param transaccion Objeto {@code Transaccion} a registrar.
     * @return La transacción registrada.
     */
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

    /**
     * Obtiene transacciones por usuario y categoría.
     *
     * @param idUsuario ID del usuario.
     * @param categoria Categoría a filtrar.
     * @return Lista de transacciones coincidentes.
     */
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
            } catch (CantidadException e) {
                System.out.println(e.getMessage());
            }
            return transacciones;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }


    /**
     * Obtiene transacciones por categoría.
     *
     * @param categoria Categoría a filtrar.
     * @return Lista de transacciones.
     */
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

        } catch (SQLException | CantidadException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
	}

    /**
     * Obtiene transacciones de un usuario en un rango de fechas.
     *
     * @param idUsuario ID del usuario.
     * @param desde Fecha de inicio.
     * @param hasta Fecha de fin.
     * @return Lista de transacciones en ese rango.
     */
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
            } catch (CantidadException e) {
                System.out.println(e.getMessage());
            }
            return transacciones;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }


    /**
     * Obtiene transacciones en un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio.
     * @param fechaFin Fecha de fin.
     * @return Lista de transacciones.
     */
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
	        } catch (SQLException | CantidadException e) {
	            System.out.println(e.getMessage());
	            return Collections.emptyList();
	        }

	}

    /**
     * Elimina una transacción por su ID.
     *
     * @param id ID de la transacción a eliminar.
     * @return {@code true} si fue eliminada, {@code false} en caso contrario.
     */
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

/**
 * Devuelve un objeto {@code Transaccion} a partir de un objeto {@code ResultSet}.
 *
 * @return Transaccion
 * @param rs ResultSet.
 */
    private Transaccion mapearTransaccion(ResultSet rs) throws SQLException, CantidadException {
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
