package ru.job4j.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Factory {

    private final Logger Log = LoggerFactory.getLogger(SqlConnect.class);

    private static final String DRIVER_CLASS_NAME = "org.sqlite.JDBC";
    private static final String url = "jdbc:sqlite:C:/bugTracker.db";

    private BasicDataSource bds;

    private Connection connection;

    Factory() {
        this.bds = new BasicDataSource();
        bds.setDriverClassName(DRIVER_CLASS_NAME);
        bds.setUrl(url);
    }

    private static final Factory instance = new Factory();

    public static Factory getInstance() {
        return instance;
    }

    /**
     * Метод получения соединения с базой данных {@link Connection}
     * @return соединение с базой данных
     */
    public Connection getConnection() throws SQLException {
        return connection = bds.getConnection();
    }

    public void close() throws SQLException {
        connection.close();
    }
}