package com.finanzas.gestor_finanzas.repositorio;


import com.finanzas.gestor_finanzas.dao.DBConnection;
import com.finanzas.gestor_finanzas.excepciones.NombreCuentaException;
import com.finanzas.gestor_finanzas.modelo.Cuenta;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CuentaRepositorio {

    public List<Cuenta> obtenerCuentas() throws NombreCuentaException {
        List<Cuenta> cuentas = new ArrayList<>();
        String sql = "SELECT * FROM cuentas";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                cuentas.add(mapearCuentas(rs));
            }

            return cuentas;

        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

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

    public List<Cuenta> obtenerPorUsuarioId(int idUsuario) throws NombreCuentaException {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cuentas;
    }

    private Cuenta mapearCuentas(ResultSet rs) throws NombreCuentaException, SQLException{
    	return new Cuenta(
                rs.getInt("id"),
                rs.getInt("id_usuario"),
                rs.getString("numero_cuenta"),
                rs.getDouble("saldo_actual"));
    }
}

