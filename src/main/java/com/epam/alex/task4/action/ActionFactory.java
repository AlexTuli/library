package com.epam.alex.task4.action;

import com.epam.alex.task4.action.redirect.AdminCabinet;
import com.epam.alex.task4.action.redirect.RedirectToRegisterUser;
import com.epam.alex.task4.action.redirect.ToIndex;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.dbcp.ConnectionPool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlexTuli on 12/1/15.
 *
 * @author Bocharnikov Alexandr
 */
public class ActionFactory {

    private DaoFactory factory;
    private static Map<String, Action> actionMap;

    public ActionFactory() {
        factory = new DaoFactory(ConnectionPool.getInstance().getConnection());
        actionMap = new HashMap<>();
        actionMap.put("check-books", new CheckBooks(factory));
        actionMap.put("index", new ToIndex(factory));
        actionMap.put("authorize", new Authorize(factory));
        actionMap.put("user-cabinet", new UserCabinet(factory));
        actionMap.put("admin-cabinet", new AdminCabinet(factory));
        actionMap.put("registration", new RedirectToRegisterUser(factory));
        actionMap.put("registration-user", new RegisteredUser(factory));
        actionMap.put("create-subscription", new CreateSubscription(factory));
        actionMap.put("create-subscription", new CreateSubscription(factory));
    }

    public Action getAction(String action) {
        return actionMap.get(action);
    }

}
