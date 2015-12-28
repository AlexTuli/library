package com.epam.alex.task4.dbcp;

import org.apache.log4j.Logger;

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

    private static final Logger log = Logger.getLogger(ConnectionPool.class);
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    //TODO get it from property
    public static final String MY_LIBRARY_URL = "jdbc:h2:tcp://127.0.0.1/E:/Back-Up/H2-db/myLibrary";
    public static final String USER_NAME = "sa";
    public static final String PASSWORD = "sa";
    public static final String JDBC_H2_DRIVER = "org.h2.Driver";
    private BlockingQueue<Connection> connections;

    private ConnectionPool() {
        log.debug("Create connection pool");
        try {
            Class.forName(JDBC_H2_DRIVER); //Driver loading
            connections = new LinkedBlockingQueue<>();
            for (int i = 0; i < 10; i++) {
                log.debug("Create connection #" + i);
                Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, USER_NAME, PASSWORD);
                log.debug("Connection is " + connection);
                connections.add(connection);
            }
        } catch (ClassNotFoundException e) {
            log.error("Trouble in Connection by getting driver");
            throw new PoolException(e);
        } catch (SQLException e) {
            log.debug("Trouble in Connection by getting connection to DB");
            throw new PoolException(e);
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(MY_LIBRARY_URL, USER_NAME, PASSWORD);
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            log.debug("Try to get connection");
            return connections.poll(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("Trouble to getConnection()");
            throw new PoolException(e);
        }
    }

    public void returnConnection(Connection connection) {
        if (connection != null)
            try {
                if (connection.isValid(500)) {
                    log.debug("Return connection to pool.");
                    connections.add(connection);
                } else {
                    log.debug("Connection is not valid. Create new one.");
                    connections.add(createConnection());
                }
            } catch (SQLException ignored) {
                // It happens only when timeout less than 0, but it never happen
            }
    }
}
