package com.epam.alex.task4.action;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlexTuli on 12/1/15.
 *
 * @author Bocharnikov Alexandr
 */
public class ActionFactory {

    private static Map<String, Action> actionMap;

    static  {
        actionMap = new HashMap<>();
        actionMap.put("check-books", new CheckBooks());
    }

    public static Action getAction(String action) {
        return actionMap.get(action);
    }

}
