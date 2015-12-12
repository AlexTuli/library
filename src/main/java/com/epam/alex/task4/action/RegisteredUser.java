package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.entity.Role;
import com.epam.alex.task4.entity.Subscription;
import com.epam.alex.task4.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by AlexTuli on 12/12/15.
 *
 * @author Bocharnikov Alexandr
 */
public class RegisteredUser extends AbstractAction {

    public RegisteredUser(DaoFactory factory) {
        super(factory);
    }

    /**
     * Create new row with new User in DB
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        //TODO add md5 encrypt
        user.setLogin(login.toLowerCase());
        user.setPassword(password);
        Role role = new Role();
        role.setRole("USER");
        role.setId(2);
        user.setRole(role);
        Subscription subscription = new Subscription();
        user.setSubscription(subscription);
        AbstractDao subscriptionDao = factory.getDao("subscription");
        AbstractDao userDao = factory.getDao("user");
        try {
            factory.startTransaction();
            userDao.create(user);
        } catch (SQLException | DaoException e) {
            try {
                factory.rollback();
            } catch (SQLException e1) {
                throw new ActionException("Can't rollback", e);
            }
            return "redirect:index&info=Can't create user";
        }
        subscription.setUser(user);
        try {
            subscriptionDao.create(subscription);
        } catch (DaoException e) {
            try {
                factory.rollback();
            } catch (SQLException e1) {
                throw new ActionException("Can't rollback", e);
            }
            return "redirect:index&info=Can't create subscription";
        }
        try {
            factory.commit();
            factory.stopTransaction();
        } catch (SQLException e) {
            throw new ActionException("Can't commit", e);
        }
        return "redirect:index&info=Can't create user";
    }
}
