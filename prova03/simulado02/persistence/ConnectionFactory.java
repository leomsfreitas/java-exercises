package prova03.simulado02.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection conn;

    public ConnectionFactory() {}

    public static Connection getConnection() throws SQLException {
        if (conn == null) conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        return conn;
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }
}
