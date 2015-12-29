package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.UserDao;
import com.epam.alex.task4.entity.Role;
import com.epam.alex.task4.entity.User;
import com.epam.alex.task4.service.Utilities;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/21/15.
 *
 * @author Bocharnikov Alexandr
 */
public class PromoteUser extends AbstractAction {

    private static final Logger log = Logger.getLogger(PromoteUser.class);

    /**
     * Promote user to Administrator
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Start to promote user");
        int id = Utilities.getId(request);
        if (id <= 0) {
            return "redirect:promote-user&info=Wrong_id";
        }
        // First, read user
        UserDao userDao = daoFactory.getDao(UserDao.class);
        startTransaction();
        User user;
        try {
            user = userDao.read(id);
        } catch (DaoException e) {
            log.error("Can't find user");
            rollback();
            daoFactory.close();
            return "redirect:promote-user&info=Can't_find_user";
        }
        //Set to user role - Admin
        Role adminRole = Role.getAdminRole();
        user.setRole(adminRole);
        //Update and commit user
        try {
            userDao.update(user);
        } catch (DaoException e) {
            log.error("Can't update user");
            rollback();
            daoFactory.close();
            return "redirect:redirect-promote-user&info=Can't_update_user";
        }

        commit();
        daoFactory.close();
        log.info("Promote successful!");
        return "redirect:admin-cabinet&info=User_promoted";
    }
}
