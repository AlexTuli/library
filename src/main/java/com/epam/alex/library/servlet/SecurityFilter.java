package com.epam.alex.library.servlet;

import com.epam.alex.library.entity.Role;
import com.epam.alex.library.entity.User;
import com.epam.alex.library.utilites.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


    private static final Logger log = LoggerFactory.getLogger(SecurityFilter.class);

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
        User userFromSession = Utilities.getUserFromSession(request);
        String actionName = Utilities.getActionName(request);

        Role role;

        if (userFromSession == null) {
            log.debug("User is not in session yet");
            role = Role.getAnonymousRole();
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
