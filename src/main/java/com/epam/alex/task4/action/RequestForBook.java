package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.dao.SubscriptionDao;
import com.epam.alex.task4.entity.Book;
import com.epam.alex.task4.entity.Subscription;
import com.epam.alex.task4.entity.User;
import com.epam.alex.task4.service.Service;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by AlexTuli on 12/13/15.
 *
 * @author Bocharnikov Alexandr
 */
public class RequestForBook extends AbstractAction {

    private static final Logger log = Logger.getLogger(RequestForBook.class);


    /**
     * Request for a new book and add it to subscription
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Start to adding book to subscription...");
        int id = Service.getId(request);
        if (id <= 0) {
            return "redirect:redirect-to-request-for-book&info=Incorrect book ID";
        }

        Book book = new Book();
        book.setId(id);
        Subscription subscription = new Subscription();
        subscription.addBook(book);

        // Here we read subscription ID of current user to set for update query
        log.debug("Get subscriptionDao");
        SubscriptionDao subscriptionDao = daoFactory.getDao(SubscriptionDao.class);
        log.debug("Get user from session");
        User user = Service.getUserFromSession(request);
        log.debug("Read subscription from DB");
        Subscription readSubscription;
        subscription.setUser(user);

        //Check that user have no book already
        try {
            readSubscription = subscriptionDao.read(user.getId());
            if (readSubscription.contains(book)) {
                log.warn("User already have this book");
                return "redirect:redirect-to-request-for-book&info=You already have this book";
            }
        } catch (DaoException e) {
            log.debug("Subscription have no books");
        }

        int idSubscription = user.getSubscription().getId();
        log.debug("Set subscription ID to subscription");
        subscription.setId(idSubscription);

        log.debug("Updating subscription");

        try {
            startTransaction();
            subscriptionDao.update(subscription);
        } catch (DaoException e) {
            log.error("Update failed", e);
            rollback();
            return "redirect:redirect-to-request-for-book&info=Can't update subscription";
        }

        commit();
        log.info("Book added successful!");
        return "redirect:user-cabinet&notification=Book added";
    }
}
