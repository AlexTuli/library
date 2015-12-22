package com.epam.alex.task4.servicer;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Utilites class for clear-up code from same blocks of code
 * Created by AlexTuli on 12/22/15.
 *
 * @author Bocharnikov Alexandr
 */
public final class Service {

    private static final Logger log = Logger.getLogger(Service.class);

    /**
     * Get "id" param from request
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


}
