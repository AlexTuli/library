package com.epam.alex.library.servlet;

import com.epam.alex.library.entity.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains lists of action that allowed to some of role
 * Created by AlexTuli on 12/24/15.
 *
 * @author Bocharnikov Alexander
 */
public final class Accessor {

    private static final Accessor INSTANCE = new Accessor();

    private List<String> allowedActionsAdmin;

    private List<String> allowedActionsUser;

    private List<String> allowedActionsAnonymous;

    private Accessor() {
        allowedActionsAdmin = new ArrayList<>();
        allowedActionsUser = new ArrayList<>();
        allowedActionsAnonymous = new ArrayList<>();
        allowedActionsAnonymous.add("index");
        allowedActionsAnonymous.add("show-page");
        allowedActionsAnonymous.add("authorize");
        allowedActionsAnonymous.add("check-books");
        allowedActionsAnonymous.add("registration");
        allowedActionsAnonymous.add("registration-user");
        allowedActionsUser.addAll(allowedActionsAnonymous);
        allowedActionsUser.add("user-cabinet");
        allowedActionsUser.add("request-for-book");
        allowedActionsUser.add("return-book");
        allowedActionsUser.add("redirect-subscriptions");
        allowedActionsAdmin.addAll(allowedActionsAnonymous);
        allowedActionsAdmin.add("get-users-list");
        allowedActionsAdmin.add("admin-cabinet");
        allowedActionsAdmin.add("delete-user");
        allowedActionsAdmin.add("create-notification");
        allowedActionsAdmin.add("promote-user");
        allowedActionsAdmin.add("add-book");
    }

    public static Accessor getInstance() {
        return INSTANCE;
    }

    /**
     * @return true if action is allowed to current role
     */
    public boolean isAllowed(String actionName, Role role) {
        switch (role.getName()) {
            case "ADMINISTRATOR":
                return allowedActionsAdmin.contains(actionName);
            case "USER":
                return allowedActionsUser.contains(actionName);
            default:
                return allowedActionsAnonymous.contains(actionName);
        }
    }
}
