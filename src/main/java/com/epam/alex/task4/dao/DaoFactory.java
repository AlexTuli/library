package com.epam.alex.task4.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlexTuli on 12/6/15.
 *
 * @author Bocharnikov Alexandr
 */
public class DaoFactory {

    private Connection connection;
    private Map<String, Object> daoMap;

    public DaoFactory(Connection connection) {
        this.connection = connection;
        daoMap = new HashMap<>();
        daoMap.put("book", new BookDao());
        daoMap.put("notification", new NotificationDao());
        daoMap.put("subscription", new SubscriptionDao());
    }

    public Object getDao(String name) {
        return daoMap.get(name);
    }

}
