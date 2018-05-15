package com.epam.alex.library.action;

import com.epam.alex.library.dao.DaoException;
import com.epam.alex.library.dao.SubscriptionDao;
import com.epam.alex.library.entity.Book;
import com.epam.alex.library.entity.Subscription;
import com.epam.alex.library.entity.User;
import com.epam.alex.library.utilites.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by AlexTuli on 12/13/15.
 *
 * @author Bocharnikov Alexandr
 */
public class RequestForBook extends AbstractAction {

    private static final Logger log = LoggerFactory.getLogger(RequestForBook.class);


    /**
     * Request for a new book and add it to subscription
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Start to adding book to subscription...");
        int id = Utilities.getId(request);
        if (id <= 0) {
            daoFactory.close();
            return "redirect:get-new-book&info=Incorrect_book_ID";
        }

        Book book = new Book();
        book.setId(id);
        Subscription subscription = new Subscription();
        subscription.addBook(book);

        // Here we read subscription ID of current user to set for update query
        log.debug("Get subscriptionDao");
        SubscriptionDao subscriptionDao = daoFactory.getDao(SubscriptionDao.class);
        log.debug("Get user from session");
        User user = Utilities.getUserFromSession(request);
        log.debug("Read subscription from DB");
        Subscription readSubscription;
        subscription.setUser(user);

        //Check that user have no this book already
        try {
            readSubscription = subscriptionDao.read(user != null ? user.getId() : 0);
            if (readSubscription.contains(book)) {
                log.warn("User already have this book");
                return "redirect:get-new-book&info=You_already_have_this_book";
            }
        } catch (DaoException e) {
            log.debug("Subscription have no books");
        } finally {
            daoFactory.close();
        }

        int idSubscription = 0;
        if (user != null) {
            idSubscription = user.getSubscription().getId();
            log.debug("ID of subscription " + idSubscription);
        } else {
            log.error("user is null");
        }
        log.debug("Set subscription ID to subscription");
        subscription.setId(idSubscription);

        log.debug("Updating subscription");

        try {
            startTransaction();
            subscriptionDao.update(subscription);
        } catch (DaoException e) {
            log.error("Update failed", e);
            rollback();
            daoFactory.close();
            return "redirect:get-new-book&info=Can't_update_subscription";
        }

        commit();
        daoFactory.close();
        log.info("Book added successful!");
        return "redirect:user-cabinet&notification=Book_added";
    }
}
