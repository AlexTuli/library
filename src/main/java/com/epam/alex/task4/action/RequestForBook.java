package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.entity.AbstractEntity;
import com.epam.alex.task4.entity.Book;
import com.epam.alex.task4.entity.Subscription;
import com.epam.alex.task4.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by AlexTuli on 12/13/15.
 *
 * @author Bocharnikov Alexandr
 */
public class RequestForBook extends AbstractAction {

    private static final Logger log = Logger.getLogger(RequestForBook.class);

    public RequestForBook(DaoFactory factory) {
        super(factory);
    }

    /**
     * Request for a new book and add it to subscription
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        // TODO: 12/14/15 Don't allow to add book twice
        log.info("Start to adding book to subscription...");
        String sId = request.getParameter("id");
        int id;
        // Check to sId is a number, not a char or something
        try {
            id = Integer.parseInt(sId);
        } catch (NumberFormatException e) {
            log.error("Incorrect number format", e);
            return "redirect:redirect-to-request-for-book&info=Incorrect book ID";
        }
        Book book = new Book();
        book.setId(id);
        Subscription subscription = new Subscription();
        subscription.addBook(book);

        // Here we read subscription ID of current user to set for update query
        log.debug("Get subscriptionDao");
        AbstractDao subscriptionDao = daoFactory.getDao("subscription");
        log.debug("Get user from session");
        User user = (User) request.getSession(false).getAttribute("user");
        log.debug("Read subscription from DB");
        Subscription readSubscription = null;
        subscription.setUser(user);
        try {
            readSubscription = (Subscription) subscriptionDao.read(subscription); //Should read only ID of subscription, without books in them
        } catch (DaoException e) {
            log.debug("Subscription doesn't exist, create new");

        }
        int idSubscription;
        if (readSubscription != null) {
            idSubscription = readSubscription.getId();
        } else {
            log.error("Subscription is null");
            return "redirect:redirect-to-request-for-book&info=Can't read subscription"; // TODO: 12/20/15 Redirect to request page
        }
        log.debug("Set User ID to subscription");
        subscription.setId(idSubscription);

        log.debug("Updating subscription");
        try {
            startTransaction();
            subscriptionDao.update(subscription);
        } catch (DaoException e) {
            log.error("Update failed", e);
            rollback();
            return "redirect:redirect-to-request-for-book&info=Incorrect book ID";
        }

        commit();
        log.info("Book added successful!");
        return "redirect:user-cabinet&notification=Book added";
    }
}
