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
        log.debug("Get connection " + connection);
    }

    /**
     * Return Dao
     *
     * @param <T> Dao.class (ex: UserDao.class)
     * @return Dao
     */
    public <T extends AbstractDao> T getDao(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor(Connection.class);
            return constructor.newInstance(connection);
        } catch (NoSuchMethodException e) {
            log.debug("No such method exception");
            throw new DaoException("No such method exception");
        } catch (InstantiationException e) {
            log.debug("Instantiation Exception");
            throw new DaoException(e);
        } catch (IllegalAccessException | InvocationTargetException ignored) {
        }
        throw new DaoException();
    }

    /**
     * Start transaction in DB
     *
     * @throws SQLException
     */
    public void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    /**
     * Commit changes to DB
     *
     * @throws SQLException
     */
    public void commit() throws SQLException {
        connection.commit();
    }

    /**
     * Rollback DB
     *
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     * Stop transaction
     *
     * @throws SQLException
     */
    public void stopTransaction() throws SQLException {
        connection.setAutoCommit(true);
    }

    /**
     * Return connection to pool
     */
    public void close() {
        if (connection != null) {
            log.debug("Connection " + connection + " returned to CP");
            connectionPool.returnConnection(connection);
        } else {
            log.debug("Connection is null");
        }
    }
}
