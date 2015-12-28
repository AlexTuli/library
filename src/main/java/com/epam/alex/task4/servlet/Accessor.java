package com.epam.alex.task4.servlet;

import com.epam.alex.task4.entity.Role;

import java.util.ArrayList;
import java.util.List;

/**
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
        allowedActionsUser.add("redirect-to-request-for-book");
        allowedActionsUser.add("redirect-to-return-book");
        allowedActionsUser.add("return-book");
        allowedActionsUser.add("return-book");
        allowedActionsUser.add("redirect-subscriptions");
        allowedActionsAdmin.addAll(allowedActionsAnonymous);
        allowedActionsAdmin.add("redirect-delete-user");
        allowedActionsAdmin.add("get-users-list");
        allowedActionsAdmin.add("redirect-promote-user");
        allowedActionsAdmin.add("admin-cabinet");
        allowedActionsAdmin.add("delete-user");
        allowedActionsAdmin.add("redirect-notify");
        allowedActionsAdmin.add("create-notification");
        allowedActionsAdmin.add("promote-user");
        allowedActionsAdmin.add("add-book");
        allowedActionsAdmin.add("redirect-add-book");
    }

    public static Accessor getInstance() {
        return INSTANCE;
    }

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
