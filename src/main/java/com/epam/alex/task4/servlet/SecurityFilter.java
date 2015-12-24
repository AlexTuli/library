package com.epam.alex.task4.servlet;

import com.epam.alex.task4.entity.Role;
import com.epam.alex.task4.entity.RoleFactory;
import com.epam.alex.task4.entity.User;
import com.epam.alex.task4.service.Service;
import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.jstl.test.PageContextImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexTuli on 12/23/15.
 *
 * @author Bocharnikov Alexandr
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = "/controller")
public class SecurityFilter implements Filter {

    private static final String PARAMETER_ACTION = "action";

    private static final Logger log = Logger.getLogger(SecurityFilter.class);

    public void destroy() {
        log.info("Filter destroyed");
    }

    /**
     * Prevent illegal access to servlet pages
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        List<String> allowedActionsAdmin = new ArrayList<>();
        allowedActionsAdmin.add("redirect-delete-user");
        allowedActionsAdmin.add("admin-cabinet");
        allowedActionsAdmin.add("redirect-delete-user");

        log.debug("Get accessor");
        Accessor accessor = Accessor.getInstance();

        log.info("doFilter()");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        User userFromSession = Service.getUserFromSession(request);
        if (userFromSession == null){
            log.debug("User is not in session yet");
            chain.doFilter(req, resp);
            return;
        }
        Role role = userFromSession.getRole();
        log.info("User is " + userFromSession);
        RoleFactory roleFactory = RoleFactory.getInstance();
        String servletPath = request.getRequestURI();
        log.info("Servlet path is " + servletPath);
        String actionName = getActionName(request);
        if (role.equals(roleFactory.getAdminRole())) {
            if (allowedActionsAdmin.contains(actionName)) {
                log.info("Access granted to " + actionName);
                //Do nothing just let enter
            } else {
                log.info("Access denied to " + actionName);
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            log.debug("It's admin here!");
        } else if (role.equals(roleFactory.getUserRole())) {
            if (accessor.isAllowed(actionName, role)) {
                log.info("Access granted to " + actionName);
            } else {
                log.info("Access denied to " + actionName);
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            log.debug("It's user here!");
        } else {
            log.debug("It's anonymous");
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        log.info("Filter started");
    }

    private String getActionName(HttpServletRequest request) {
        return request.getParameter(PARAMETER_ACTION);
    }

}
