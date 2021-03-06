package com.epam.alex.library.action;

import com.epam.alex.library.dao.DaoException;
import com.epam.alex.library.dao.NotificationDao;
import com.epam.alex.library.dao.UserDao;
import com.epam.alex.library.entity.Notification;
import com.epam.alex.library.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/18/15.
 *
 * @author Bocharnikov Alexandr
 */
public class CreateNotification extends AbstractAction {

    private static final Logger log = LoggerFactory.getLogger(CreateNotification.class);

    /**
     * Create notification
     * Not implement yet :D
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Start to creating notification");
        String stringUserID = request.getParameter("user-id");
        String notificationText = request.getParameter("notification");

        int id;
        try {
            id = Integer.parseInt(stringUserID);
        } catch (NumberFormatException e) {
            daoFactory.close();
            log.error("Wrong number format");
            return "redirect:redirect-notify&info=Wrong number format";
        }

        NotificationDao notificationDao = daoFactory.getDao(NotificationDao.class);


        Notification notification = new Notification();
        notification.setText(notificationText);

        UserDao userDao = daoFactory.getDao(UserDao.class);

        User user;
        try {
            log.debug("Read user in db with ID " + id);
            user = userDao.read(id);
        } catch (DaoException e) {
            daoFactory.close();
            log.error("Can't read user, wrong ID");
            return "redirect:redirect-notify&info=Wrong ID";
        }

        notification.setUser(user);
        try {
            log.debug("Start transaction");
            startTransaction();
            log.debug("Create notification");
            notificationDao.create(notification);
        } catch (DaoException e) {
            log.error("Can't create notification, rollback", e);
            rollback();
            daoFactory.close();
            return "redirect:redirect-notify&info=Can't create notification";
        }

        commit();
        daoFactory.close();
        log.info("Notification create successfully!");
        return "redirect:admin-cabinet&info=Notification create successfully!";
    }
}
