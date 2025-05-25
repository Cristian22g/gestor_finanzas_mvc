package com.finanzas.gestor_finanzas.repositorio;


import com.finanzas.gestor_finanzas.dao.DBConnection;
import com.finanzas.gestor_finanzas.excepciones.CantidadException;
import com.finanzas.gestor_finanzas.excepciones.NombreCuentaException;
import com.finanzas.gestor_finanzas.modelo.Cuenta;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repositorio encargado de gestionar el acceso a datos relacionados con cuentas financieras.
 */
public class CuentaRepositorio {

    /**
     * Obtiene todas las cuentas registradas.
     *
     * @return Lista de cuentas.
     */
    public List<Cuenta> obtenerCuentas() throws NombreCuentaException {
        List<Cuenta> cuentas = new ArrayList<>();
        String sql = "SELECT * FROM cuentas";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                try {
                    cuentas.add(mapearCuentas(rs));
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

            return cuentas;

        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Registra una nueva cuenta.
     *
     * @param cuenta Objeto {@code Cuenta} a registrar.
     * @return La cuenta registrada.
     */
    public Cuenta registrarCuenta(Cuenta cuenta) {
        String sql = "INSERT INTO cuentas (id_usuario, nombre_cuenta, saldo_actual) VALUES (?, ?, ?)";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, cuenta.getIdUsuario());
            ps.setString(2, cuenta.getNombreCuenta());
            ps.setDouble(3, cuenta.getSaldoActual());

            int result = ps.executeUpdate();
            return result > 0 ? cuenta : null;

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Elimina una cuenta por su ID.
     *
     * @param id ID de la cuenta a eliminar.
     * @return {@code true} si se eliminó correctamente, {@code false} si no se encuentra.
     */
    public boolean eliminarCuenta(int id) {
        String sql = "DELETE FROM cuentas WHERE id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene las cuentas asociadas a un usuario específico.
     *
     * @param idUsuario ID del usuario.
     * @return Lista de cuentas del usuario.
     */
    public List<Cuenta> obtenerCuentasPorUsuarioId(int idUsuario) throws NombreCuentaException {
        List<Cuenta> cuentas = new ArrayList<>();
        String sql = "SELECT * FROM cuentas WHERE id_usuario = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cuentas.add(mapearCuentas(rs));
            }

        } catch (SQLException | CantidadException e) {
            e.printStackTrace();
        }

        return cuentas;
    }

    private Cuenta mapearCuentas(ResultSet rs) throws NombreCuentaException, SQLException, CantidadException {
    	return new Cuenta(
                rs.getInt("id"),
                rs.getInt("id_usuario"),
                rs.getString("nombre_cuenta"),
                rs.getDouble("saldo_actual"));
    }

    public boolean cambioSaldo(int idCuenta, double nuevoSaldo) {
        String sql = "UPDATE CUENTAS SET  SALDO_ACTUAL = ? WHERE ID = ?";

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ){
            ps.setDouble(1, nuevoSaldo);
            ps.setInt(2, idCuenta);

            ps.executeUpdate();

        }catch (SQLException e){
            return false;
        }
        return true;
    }
}

