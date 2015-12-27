package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.dao.UserDao;
import com.epam.alex.task4.entity.User;
import com.epam.alex.task4.service.Service;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by AlexTuli on 12/19/15.
 *
 * @author Bocharnikov Alexandr
 */
public class DeleteUser extends AbstractAction {

    private static final Logger log = Logger.getLogger(DeleteUser.class);


    /**
     * Delete user from DB
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        User sessionUser = (User) session.getAttribute("user");

        log.info("Start to delete user");

        int userId = Service.getId(request);

        if (sessionUser.getId() == userId) {
            daoFactory.close();
            return "redirect:redirect-delete-user&info=Can't delete yourself";
        }

        UserDao userDao = daoFactory.getDao(UserDao.class);

        User user = new User();
        user.setId(userId);

        try {
            startTransaction();
            log.debug("Start to delete user in Dao");
            int delete = userDao.delete(user);
            if (delete == 0) {
                log.error("User not found, can't delete");
                daoFactory.close();
                return "redirect:redirect-delete-user&info=User not found";
            } else if (delete > 1) {
                log.error("More than one user, something gonna wrong");
                rollback();
                daoFactory.close();
                return "redirect:redirect-delete-user&info=Something gonna wrong, try again";
            }
        } catch (DaoException e) {
            log.error("Can't delete user");
            rollback();
            daoFactory.close();
            return "redirect:redirect-delete-user&info=Can't delete user";
        }

        commit();

        daoFactory.close();
        log.info("User delete successfully!");
        return "redirect:admin-cabinet&info=User deleted!";
    }
}
