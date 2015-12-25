package com.epam.alex.task4.servlet;

import com.epam.alex.task4.entity.Role;
import com.epam.alex.task4.entity.User;
import com.epam.alex.task4.service.Service;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by AlexTuli on 12/23/15.
 *
 * @author Bocharnikov Alexandr
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = "/controller")
public class SecurityFilter implements Filter {


    private static final Logger log = Logger.getLogger(SecurityFilter.class);

    public void destroy() {
        log.info("Security filter destroyed");
    }

    /**
     * Prevent illegal access to servlet pages
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        log.info("doFilter()");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        log.debug("Get accessor");
        Accessor accessor = Accessor.getInstance();

        log.debug("Get user from session");
        User userFromSession = Service.getUserFromSession(request);
        String actionName = Service.getActionName(request);

        Role role;

        if (userFromSession == null) {
            log.debug("User is not in session yet");
            role = new Role("Anonymous");
        } else {
            role = userFromSession.getRole();
            log.info("User is " + userFromSession);
        }
        if (accessor.isAllowed(actionName, role)) {
            log.debug("Access to " + actionName + " to " + role + " granted");
        } else {
            log.debug("Access denied to " + role + " for action " + actionName);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        log.info("Security filter started");
    }
}
