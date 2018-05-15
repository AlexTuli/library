package com.epam.alex.library.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexander on 27.12.2015.
 *
 * @author Alexander Bocharnikov
 */
public class ShowPage extends AbstractAction {

    private static final Logger log = LoggerFactory.getLogger(ShowPage.class);
    public static final String JSP_EXTENSION = ".jsp";
    public static final String PATH_TO_JSP = "/WEB-INF/jsp/";

    /**
     * Forward to page that content in parameter "redirect"
     */
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
