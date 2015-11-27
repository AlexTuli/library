package com.epam.alex.task4.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by AlexTuli on 11/27/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Connection {

    public static final String MY_LIBRARY_URL = "jdbc:h2:~/temp/library/myLibrary";

    /**
     * Load driver
     */
    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new ConnectionException("Trouble in Connection by getting driver", e);
        }
    }

    //TODO ADD USER IN PARAM
    public static java.sql.Connection getConnection() {
        java.sql.Connection connection;

        try {
            connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa");
        } catch (SQLException e) {
            throw new ConnectionException("Trouble with getting connection", e);
        }

        return connection;
    }
}
