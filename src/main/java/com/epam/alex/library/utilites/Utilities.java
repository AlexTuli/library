package com.epam.alex.library.utilites;

import com.epam.alex.library.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Utilities class for clear-up code from same blocks of code
 * Created by AlexTuli on 12/22/15.
 *
 * @author Bocharnikov Alexandr
 */
public final class Utilities {

    private static final String PARAMETER_ACTION = "action";

    private static final Logger log = Logger.getLogger(Utilities.class);

    /**
     * Get "id" param from request
     *
     * @return id, or -1 if it's not a number
     */
    public static int getId(HttpServletRequest request) {
        String stringId = request.getParameter("id");
        if (stringId == null) {
            return -1;
        }
        int id;
        try {
            id = Integer.parseInt(stringId);
        } catch (NumberFormatException e) {
            log.error("Wrong number format");
            return -1;
        }
        return id;
    }

    /**
     * Get attribute user from session
     *
     * @return User from session
     */
    public static User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (User) session.getAttribute("user");
        }
        return null;
    }

    /**
     * Get parameter action from request
     *
     * @return String action parameter
     */
    public static String getActionName(HttpServletRequest request) {
        return request.getParameter(PARAMETER_ACTION);
    }


}
