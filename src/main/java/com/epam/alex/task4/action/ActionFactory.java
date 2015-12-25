package com.epam.alex.task4.action;

import com.epam.alex.task4.action.redirect.*;
import com.epam.alex.task4.dao.DaoFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlexTuli on 12/1/15.
 *
 * @author Bocharnikov Alexandr
 */
public class ActionFactory {

    private static final ActionFactory INSTANCE = new ActionFactory();
    private DaoFactory daoFactory;
    private static Map<String, Action> actionMap;

    private ActionFactory() {
        daoFactory = DaoFactory.getInstance();
        actionMap = new HashMap<>();
        actionMap.put("check-books", new CheckBooks(daoFactory));
        actionMap.put("index", new ToIndex(daoFactory));
        actionMap.put("authorize", new Authorize(daoFactory));
        actionMap.put("user-cabinet", new UserCabinet(daoFactory));
        actionMap.put("admin-cabinet", new AdminCabinet(daoFactory));
        actionMap.put("registration", new RedirectToRegisterUser(daoFactory));
        actionMap.put("registration-user", new RegisteredUser(daoFactory));
        actionMap.put("create-subscription", new CreateSubscription(daoFactory)); //WTF
        actionMap.put("request-for-book", new RequestForBook(daoFactory));
        actionMap.put("redirect-to-request-for-book", new RedirectToRequestForBook(daoFactory));
        actionMap.put("redirect-to-return-book", new RedirectToReturnBook(daoFactory));
        actionMap.put("return-book", new ReturnBook(daoFactory));
        actionMap.put("add-book", new AddBook(daoFactory));
        actionMap.put("redirect-add-book", new RedirectAddBook(daoFactory));
        actionMap.put("get-users-list", new GetUsersList(daoFactory));
        actionMap.put("redirect-notify", new RedirectToNotify(daoFactory));
        actionMap.put("create-notification", new CreateNotification(daoFactory));
        actionMap.put("redirect-delete-user", new RedirectToDeleteUser(daoFactory));
        actionMap.put("delete-user", new DeleteUser(daoFactory));
        actionMap.put("redirect-promote-user", new RedirectToPromoteUser(daoFactory));
        actionMap.put("promote-user", new PromoteUser(daoFactory));
        actionMap.put("redirect-subscriptions", new RedirectToSubcription(daoFactory));
    }

    public static ActionFactory getInstance() {
        return INSTANCE;
    }

    public Action getAction(String action) {
        return actionMap.get(action);
    }

}
