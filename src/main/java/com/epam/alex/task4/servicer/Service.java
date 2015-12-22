package com.epam.alex.task4.servicer;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by AlexTuli on 12/22/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Service {

    private static final Logger log = Logger.getLogger(Service.class);

    public static int getId(HttpServletRequest request) {
        String stringId = request.getParameter("id");
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
