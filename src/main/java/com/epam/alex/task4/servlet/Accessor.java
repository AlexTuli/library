package com.epam.alex.task4.servlet;

import com.epam.alex.task4.entity.Role;


import java.util.List;
import java.util.Map;

/**
 * Created by AlexTuli on 12/24/15.
 *
 * @author Bocharnikov Alexandr
 */
public final class Accessor {

    private static final Accessor INSTANCE = new Accessor();

    private List<String> allowedActionsAdmin;

    private List<String> allowedActionsUser;

    private List<String> allowedActionsAnonymous;

    Map<String, String> aMap;



    private Accessor() {
        allowedActionsAnonymous.add("index");
        allowedActionsAnonymous.add("authorize");
        allowedActionsAnonymous.add("check-books");
        allowedActionsAnonymous.add("registration");
        allowedActionsAnonymous.add("registration-user");
        allowedActionsUser.add("user-cabinet");
        allowedActionsUser.add("request-for-book");
        allowedActionsUser.add("redirect-to-request-for-book");
        allowedActionsUser.add("redirect-to-return-book");
        allowedActionsUser.add("return-book");
        allowedActionsUser.add("return-book");
        allowedActionsUser.add("redirect-subscriptions");
        allowedActionsAdmin.add("redirect-delete-user");
        allowedActionsAdmin.add("admin-cabinet");
    }

    public static Accessor getInstance() {
        return INSTANCE;
    }

    public boolean isAllowed (String actionName, Role role) {
        switch (role.getRole()) {
            case "ADMINISTRATOR":
                return allowedActionsAdmin.contains(actionName);
            case "USER":
                return allowedActionsUser.contains(actionName);
            default:
                return allowedActionsAnonymous.contains(actionName);
        }
    }
}
