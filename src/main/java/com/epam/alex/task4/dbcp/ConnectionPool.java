package com.epam.alex.task4.dbcp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by AlexTuli on 11/27/15.
 *
 * @author Bocharnikov Alexandr
 */
public class ConnectionPool {

    private static final ConnectionPool INSTANCE = new ConnectionPool();
    public static final String MY_LIBRARY_URL = "jdbc:h2:tcp://127.0.0.1/~/temp/library/myLibrary";
    public static final String USER_NAME = "sa";
    public static final String PASSWORD = "sa";
    private BlockingQueue<Connection> connections;

    private ConnectionPool() {
        try {
            Class.forName("org.h2.Driver"); //Driver loading
            connections = new LinkedBlockingQueue<>();
            for (int i = 0; i < 10; i++) {
                Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, USER_NAME, PASSWORD);
                connections.add(connection);
            }
        } catch (ClassNotFoundException e) {
            throw new ConnectionException("Trouble in Connection by getting driver", e);
        } catch (SQLException e) {
            throw new ConnectionException("Trouble in Connection by getting connection to DB", e);
        }
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            return connections.poll(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new ConnectionException("Trouble to getConnection()", e);
        }
    }

    public void returnConnection(Connection connection) {
        if (connection != null)
            connections.add(connection);
    }
}
