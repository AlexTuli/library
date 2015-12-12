package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.entity.Role;
import com.epam.alex.task4.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by AlexTuli on 12/1/15.
 *
 * @author Bocharnikov Alexandr
 */
public class Authorize extends AbstractAction {


    public Authorize(DaoFactory factory) {
        super(factory);
    }

    /**
     * Authorize users to server
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = new User();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        user.setLogin(login.toLowerCase());
        user.setPassword(password);

        AbstractDao Dao = factory.getDao("user");

        try {
            user = (User) Dao.read(user);
        } catch (DaoException e) {
            return "redirect:index&info=User not found";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        Role role = user.getRole();

        if (role.getRole().equalsIgnoreCase("USER")) {
            return "redirect:user-cabinet";
        } else if (role.getRole().equalsIgnoreCase("ADMINISTRATOR")) {
            return "redirect:admin-cabinet";
        }
        return "redirect:index&info=Something gonna wrong...";

    }

}
