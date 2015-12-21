package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.entity.User;
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

    public DeleteUser(DaoFactory factory) {
        super(factory);
    }

    /**
     * Delete user from DB
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        User sessionUser = (User) session.getAttribute("user");

        log.info("Start to delete user");
        String stringUserId = request.getParameter("id");
        int userId;
        try {
            userId = Integer.parseInt(stringUserId);
        } catch (NumberFormatException  e) {
            log.error("Wrong ID format", e);
            return "redirect:redirect-delete-user&info=Wrong number format";
        }

        if (sessionUser.getId() == userId) {
            return "redirect:redirect-delete-user&info=Can't delete yourself";
        }

        AbstractDao userDao = daoFactory.getDao("user");

        User user = new User();
        user.setId(userId);

        try {
            startTransaction();
            log.debug("Start to delete user in Dao");
            int delete = userDao.delete(user);
            if (delete == 0) {
                log.error("User not found, can't delete");
                return "redirect:redirect-delete-user&info=User not found";
            } else if (delete > 1) {
                log.error("More than one user, something gonna wrong");
                rollback();
                return "redirect:redirect-delete-user&info=Something gonna wrong, try again";
            }
        } catch (DaoException e) {
            log.error("Can't delete user");
            rollback();
            return "redirect:redirect-delete-user&info=Can't delete user";
        }

        commit();

        log.info("User delete successfully!");
        return "redirect:admin-cabinet&info=User deleted!";
    }
}
