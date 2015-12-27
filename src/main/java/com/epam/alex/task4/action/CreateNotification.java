package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.*;
import com.epam.alex.task4.entity.Notification;
import com.epam.alex.task4.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/18/15.
 *
 * @author Bocharnikov Alexandr
 */
public class CreateNotification extends AbstractAction {

    private static final Logger log = Logger.getLogger(CreateNotification.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Start to creating notification");
        String stringUserID = request.getParameter("user-id");
        String notificationText = request.getParameter("notification");

        int id;
        try {
            id = Integer.parseInt(stringUserID);
        } catch (NumberFormatException e) {
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
            log.error("Can't read user, wrong ID", e);
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
            return "redirect:redirect-notify&info=Can't create notification";
        }

        commit();
        log.info("Notification create successfully!");
        return "redirect:admin-cabinet&info=Notification create successfully!";
    }
}
