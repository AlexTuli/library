package com.epam.alex.task4.servlet;

import com.epam.alex.task4.action.Action;
import com.epam.alex.task4.action.ActionFactory;
import com.epam.alex.task4.service.Service;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by AlexTuli on 11/22/15.
 *
 * @author Bocharnikov Alexandr
 */

@WebServlet(name = "MainServlet", urlPatterns = "/controller")
public class MainServlet extends HttpServlet {


    private final static Logger logger = Logger.getLogger(MainServlet.class);

    private ActionFactory factory;

    @Override
    public void init() throws ServletException {
        factory = ActionFactory.getInstance();
        logger.info("Servlet started");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        logger.debug("Start process request");
        String actionName = Service.getActionName(request);
        logger.debug("Get Action " + actionName);
        Action action = factory.getAction(actionName);
        logger.debug("Action " + action + " start");
        String view = action.execute(request, response);
        if (view == null) {
            request.getRequestDispatcher(getServletContext().getContextPath() + "/index.jsp").forward(request, response);
            return;
        }
        if (view.startsWith("redirect:")) {
            logger.debug("Redirecting");
            response.sendRedirect(getRedirectLocation(view));
            return;
        }
        logger.debug("forwarding");
        request.getRequestDispatcher(view).forward(request, response);
    }

    @Override
    public void destroy() {
        logger.info("Servlet destroyed");
    }

    private String getRedirectLocation(String view) {
        return getServletContext().getContextPath() + "/controller?action=" + view.substring(9);
    }
}
