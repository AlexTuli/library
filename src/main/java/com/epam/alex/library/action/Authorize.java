package com.epam.alex.library.action;

import com.epam.alex.library.dao.DaoException;
import com.epam.alex.library.dao.UserDao;
import com.epam.alex.library.entity.Role;
import com.epam.alex.library.entity.User;

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
        log.debug("Failed to read user");
        user.setPassword(password);

        log.debug("User login: " + user.getLogin() + ". User password: " + user.getPassword() + ". Get userDao");
        UserDao userDao = daoFactory.getDao(UserDao.class);

        try {
            log.debug("Try to read User in DB");
            user = userDao.read(user);
        } catch (DaoException e) {
            daoFactory.close();
            log.error("Failed to read user");
            return "redirect:index&info=User_not_found";
        }

        log.debug("Open session, with user " + user.getLogin());
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        Role role = user.getRole();

        log.debug("Redirecting by role");
        if (role.getName().equalsIgnoreCase("USER")) {
            daoFactory.close();
            log.info("Authorize successfully");
            return "redirect:user-cabinet";
        } else if (role.getName().equalsIgnoreCase("ADMINISTRATOR")) {
            daoFactory.close();
            log.info("Authorize successfully");
            return "redirect:admin-cabinet";
        }
        daoFactory.close();
        log.error("Incorrect role");
        return "redirect:index&info=Incorrect_role";

    }

}
