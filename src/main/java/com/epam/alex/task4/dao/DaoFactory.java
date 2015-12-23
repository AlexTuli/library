package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.Subscription;

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

//    private static final String SUBSCRIPTION_CLASS_NAME = Subscription.class.getName();
    private Connection connection;
    private Map<String, AbstractDao> daoMap;

    // todo make DaoFactory singleton and delete Map
    public DaoFactory(Connection connection) {
        this.connection = connection;
        daoMap = new HashMap<>();
        daoMap.put("book", new BookDao(connection, this));
        daoMap.put("notification", new NotificationDao(connection, this));
        daoMap.put("subscription", new SubscriptionDao(connection, this));
        daoMap.put("user", new UserDao(connection, this));
        daoMap.put("role", new RoleDao(connection, this));
    }

    public AbstractDao getDao(String name) {
        return daoMap.get(name);
    }

    //// TODO: 12/23/15 Get dao by dao.class, not by string

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

    /* getDao(Class clazz)
      * clazz.getName();
       * return */

}
