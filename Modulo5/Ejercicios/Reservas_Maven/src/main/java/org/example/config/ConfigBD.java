package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigBD {
    private static final String URL = "jdbc:mysql://localhost:3306/sist_reservas";
    private static final String USER = "root";
    private static final String PASSWORD = "idQ6kwx+";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
