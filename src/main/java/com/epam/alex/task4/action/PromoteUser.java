package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.entity.Role;
import com.epam.alex.task4.entity.RoleFactory;
import com.epam.alex.task4.entity.User;
import com.epam.alex.task4.servicer.Service;
import org.apache.log4j.Logger;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/21/15.
 *
 * @author Bocharnikov Alexandr
 */
public class PromoteUser extends AbstractAction {

    private static final Logger log = Logger.getLogger(PromoteUser.class);

    public PromoteUser(DaoFactory factory) {
        super(factory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Start to promote user");

        int id = Service.getId(request);
        if (id <= 0) {
            return "redirect:redirect-promote-user&info=Wrong id";
        }

        AbstractDao userDao = daoFactory.getDao("user");

        startTransaction();
        User user;
        try {
            user = (User) userDao.read(id);
        } catch (DaoException e) {
            log.error("Can't find user");
            rollback();
            return "redirect:redirect-promote-user&info=Can't find user";
        }

        Role adminRole = RoleFactory.getInstance().getAdminRole();
        user.setRole(adminRole);

        try {
            userDao.update(user);
        } catch (DaoException e) {
            log.error("Can't update user");
            rollback();
            return "redirect:redirect-promote-user&info=Can't update user";
        }

        commit();
        log.info("Promote successful!");
        return "redirect:admin-cabinet&info=User promoted";
    }
}
