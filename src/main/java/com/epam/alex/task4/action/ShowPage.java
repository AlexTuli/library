package com.epam.alex.task4.action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexander on 27.12.2015.
 *
 * @author Alexander Bocharnikov
 */
public class ShowPage extends AbstractAction {

    private static final Logger log = Logger.getLogger(ShowPage.class);
    public static final String JSP_EXTENSION = ".jsp";
    public static final String PATH_TO_JSP = "/WEB-INF/jsp/";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        daoFactory.close();
        String pageName = request.getParameter("redirect");
        if (pageName != null) {
            if (pageName.equals("index")) {
                // it need because index not in /jsp/ folder
                return request.getContextPath() + pageName + JSP_EXTENSION;
            }
            return request.getContextPath() + PATH_TO_JSP + pageName + JSP_EXTENSION;
        }
        log.error("page name is null");
        return null;
    }
}
