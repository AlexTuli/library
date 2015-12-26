package com.epam.alex.task4.dao;

import com.epam.alex.task4.dbcp.ConnectionPool;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlexTuli on 12/6/15.
 *
 * @author Bocharnikov Alexandr
 */
public class DaoFactory {

    // TODO Delete Map

    private static final Logger log = Logger.getLogger(DaoFactory.class);
    private static final DaoFactory INSTANCE = new DaoFactory();
    private Connection connection;
    private Map<String, AbstractDao> daoMap;
    private ConnectionPool connectionPool;

    private DaoFactory() {

        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        daoMap = new HashMap<>();
        daoMap.put("book", new BookDao(connection));
        daoMap.put("notification", new NotificationDao(connection));
        daoMap.put("subscription", new SubscriptionDao(connection));
        daoMap.put("user", new UserDao(connection));
        daoMap.put("role", new RoleDao(connection));
    }

    public AbstractDao getDao(String name) {
        return daoMap.get(name);
    }

    public <T extends AbstractDao> T getDаo(Class<T> clazz)  {
        try {
            Constructor<T> constructor = clazz.getConstructor(Connection.class);
            return constructor.newInstance(connectionPool.getConnection());
        } catch (NoSuchMethodException e) {
            log.debug("No such method");
        } catch (IllegalAccessException e) {
            log.debug("IllegalAccessException");
        } catch (InstantiationException e) {
            log.debug("InstantiationException");
        } catch (InvocationTargetException e) {
            log.debug("InvocationTargetException");
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

    public static DaoFactory getInstance() {
        return INSTANCE;
    }


}
