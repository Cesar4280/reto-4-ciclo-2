package models.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SQLite {

    protected String sql = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;
    protected Connection conexion = null;
    protected PreparedStatement preparada = null;

    private static final String url = "jdbc:sqlite:src/models/data/estudio.db";

    protected Connection getConexion() {

        try {
            conexion = DriverManager.getConnection(url);
            System.out.println("Conexión exitosa");

        } catch (SQLException e) {
            System.out.println("Conexión fallida");
            System.out.println(e.getMessage());
        }

        return conexion;
    }
}
