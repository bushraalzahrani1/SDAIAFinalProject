package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MCPConnection {

    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\FinalProject\\FinalProject4.db";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);

        return conn;
    }
}
