package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.entity.Role;
import com.epam.alex.task4.entity.User;
import com.sun.istack.internal.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by AlexTuli on 12/1/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Authorize extends AbstractAction {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Authorize.class);
    private static final Logger logger = Logger.getLogger(Authorize.class);

    public Authorize(DaoFactory factory) {
        super(factory);
    }

    /**
     * Authorize users to server
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.debug("Creating user");
        User user = new User();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        user.setLogin(login.toLowerCase());
        user.setPassword(password);

        log.debug("User login: " + user.getLogin() + "User password: " + user.getPassword() + "\nGet userDao");
        AbstractDao Dao = daoFactory.getDao("user");

        try {
            log.debug("Try to read User in DB");
            user = (User) Dao.read(user);
        } catch (DaoException e) {
            log.debug("Failed to read user");
            return "redirect:index&info=User not found";
        }

        log.debug("Open session, with user " + user.getLogin());
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        Role role = user.getRole();

        log.debug("Redirecting by role");
        if (role.getRole().equalsIgnoreCase("USER")) {
            log.debug("Authorize successfully");
            return "redirect:user-cabinet";
        } else if (role.getRole().equalsIgnoreCase("ADMINISTRATOR")) {
            log.debug("Authorize successfully");
            return "redirect:admin-cabinet";
        }
        log.debug("Incorrect role");
        return "redirect:index&info=Something gonna wrong...";

    }

}
