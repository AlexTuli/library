package com.epam.alex.task4.dao;

import com.epam.alex.task4.dbcp.ConnectionPool;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * Created by AlexTuli on 12/6/15.
 *
 * @author Bocharnikov Alexandr
 */
public class DaoFactory {


    private static final Logger log = Logger.getLogger(DaoFactory.class);
    private Connection connection;
    private ConnectionPool connectionPool;

    public DaoFactory() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    public <T extends AbstractDao> T getDao(Class<T> clazz)  {
        try {
            Constructor<T> constructor = clazz.getConstructor(Connection.class);
            return constructor.newInstance(connection);
        } catch (NoSuchMethodException e) {
            log.debug("No such method exception");
            throw new DaoException("No such method exception");
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException ignored) {
        }
        throw new DaoException();
    }

    public void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public void stopTransaction() throws SQLException {
        connection.setAutoCommit(true);
    }

    public void close() {
        log.debug("Connection " + connection + " returned to CP");
        connectionPool.returnConnection(connection);
    }
}
