package com.finanzas.gestor_finanzas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad que proporciona una conexión a la base de datos MySQL
 * para el sistema de gestión financiera.
 */
public class DBConnection {

    /** URL de conexión a la base de datos. */
    private static final String URL = "jdbc:mysql://localhost:3306/gestorfinancias?useSSL=false&serverTimezone=UTC";

    /** Nombre de usuario para la base de datos. */
    private static final String USUARIO = "root";

    /** Contraseña del usuario para la base de datos. */
    private static final String CONTRASENA = "123a";

    /**
     * Constructor vacío. No se necesita crear instancias de esta clase.
     */
    private DBConnection(){}

    /**
     * Establece y devuelve una conexión activa con la base de datos.
     *
     * @return Un objeto {@link Connection} para interactuar con la base de datos.
     * @throws SQLException Si ocurre un error al intentar conectarse.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
