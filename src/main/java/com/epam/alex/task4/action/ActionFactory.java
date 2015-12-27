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
    private static Map<String, Action> actionMap;

    private ActionFactory() {
        actionMap = new HashMap<>();
        actionMap.put("check-books", new CheckBooks());
        actionMap.put("index", new ToIndex());
        actionMap.put("authorize", new Authorize());
        actionMap.put("user-cabinet", new UserCabinet());
        actionMap.put("admin-cabinet", new AdminCabinet());
        actionMap.put("registration", new RedirectToRegisterUser());
        actionMap.put("registration-user", new RegisteredUser());
        actionMap.put("request-for-book", new RequestForBook());
        actionMap.put("redirect-to-request-for-book", new RedirectToRequestForBook());
        actionMap.put("redirect-to-return-book", new RedirectToReturnBook());
        actionMap.put("return-book", new ReturnBook());
        actionMap.put("add-book", new AddBook());
        actionMap.put("redirect-add-book", new RedirectAddBook());
        actionMap.put("get-users-list", new GetUsersList());
        actionMap.put("redirect-notify", new RedirectToNotify());
        actionMap.put("create-notification", new CreateNotification());
        actionMap.put("redirect-delete-user", new RedirectToDeleteUser());
        actionMap.put("delete-user", new DeleteUser());
        actionMap.put("redirect-promote-user", new RedirectToPromoteUser());
        actionMap.put("promote-user", new PromoteUser());
        actionMap.put("redirect-subscriptions", new RedirectToSubcription());
    }

    public static ActionFactory getInstance() {
        return INSTANCE;
    }

    public Action getAction(String action) {
        return actionMap.get(action);
    }

}
