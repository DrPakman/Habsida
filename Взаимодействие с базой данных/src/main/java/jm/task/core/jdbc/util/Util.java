package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // Данные для подключения к базе данных
    private static final String URL = "jdbc:mysql://localhost:3306/example_schema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    // Метод для получения подключения к базе данных
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
