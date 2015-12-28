package com.epam.alex.task4.action;


import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlexTuli on 12/1/15.
 *
 * @author Bocharnikov Alexandr
 */
public class ActionFactory {

    private static final Logger log = Logger.getLogger(ActionFactory.class);
    private static final ActionFactory INSTANCE = new ActionFactory();
    private static Map<String, Class<? extends AbstractAction>> actionMap;

    private ActionFactory() {
        actionMap = new HashMap<>();
        actionMap.put("check-books", CheckBooks.class);
        actionMap.put("authorize", Authorize.class);
        actionMap.put("registration-user", RegisteredUser.class);
        actionMap.put("request-for-book", RequestForBook.class);
        actionMap.put("return-book", ReturnBook.class);
        actionMap.put("add-book", AddBook.class);
        actionMap.put("get-users-list", GetUsersList.class);
        actionMap.put("create-notification", CreateNotification.class);
        actionMap.put("delete-user", DeleteUser.class);
        actionMap.put("promote-user", PromoteUser.class);
        actionMap.put("redirect-subscriptions", ShowSubscription.class);
        actionMap.put("show-page", ShowPage.class);
    }

    public static ActionFactory getInstance() {
        return INSTANCE;
    }

    public Action getAction(String action) {
        Class<? extends AbstractAction> aClass = actionMap.get(action);
        try {
            return aClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Can't get Action " + action);
            throw new ActionException(e);
        }
    }

}
