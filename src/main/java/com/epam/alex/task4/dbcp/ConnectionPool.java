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

    // TODO Figure out how this work (if it's work :D)

    private static final Logger log = Logger.getLogger(ConnectionPool.class);
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    //TODO get it from property
    public static final String MY_LIBRARY_URL = "jdbc:h2:tcp://127.0.0.1/~/H2-db/myLibrary";
    public static final String USER_NAME = "sa";
    public static final String PASSWORD = "sa";
    public static final String JDBC_H2_DRIVER = "org.h2.Driver";
    private BlockingQueue<Connection> connections;

    private ConnectionPool() {
        try {
            Class.forName(JDBC_H2_DRIVER); //Driver loading
            connections = new LinkedBlockingQueue<>();
            for (int i = 0; i < 10; i++) {
                Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, USER_NAME, PASSWORD);;
                connections.add(connection);
            }
        } catch (ClassNotFoundException e) {
            throw new PoolException("Trouble in Connection by getting driver", e);
        } catch (SQLException e) {
            throw new PoolException("Trouble in Connection by getting connection to DB", e);
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
            return connections.poll(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new PoolException("Trouble to getConnection()", e);
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
