package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * SqlConnect
 * Класс для получения соединения с базой данных.
 *
 * @author Mikhailov Sergey
 * @since 23.07.18
 */
public enum SqlConnect {
    INSTANCE;

    private final Logger Log = LoggerFactory.getLogger(SqlConnect.class);
    Connection connection = null;

    SqlConnect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/bugTracker.db");
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Метод получения соединения с базой данных {@link Connection}
     * @return соединение с базой данных
     */
    public Connection getConnection() {
        return connection;
    }
}