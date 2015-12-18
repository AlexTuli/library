package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
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
            return "redirect:user-cabinet&notification=Incorrect book ID";
        }
        Book book = new Book();
        book.setId(id);
        Subscription subscription = new Subscription();
        subscription.addBook(book);

        log.debug("Get subscriptionDao");
        AbstractDao subscriptionDao = daoFactory.getDao("subscription");
        log.debug("Get user from session");
        User user = (User) request.getSession(false).getAttribute("user");
        int idSubscription = subscriptionDao.read(user.getId()).getId();
        subscription.setId(idSubscription);

        log.debug("Updating subscription");
        try {
            daoFactory.startTransaction();
            subscriptionDao.update(subscription);
        } catch (DaoException e) {
            log.error("Update failed", e);
            try {
                daoFactory.rollback();
                daoFactory.stopTransaction();
            } catch (SQLException e1) {
                log.error("Failed to rollback", e1);
                throw new ActionException(e1);
            }
            return "redirect:user-cabinet&notification=Incorrect book ID";
        } catch (SQLException e) {
            log.error("Failed to start transaction", e);
            throw new ActionException(e);
        }
        //Commit and stop transaction
        try {
            daoFactory.commit();
            daoFactory.stopTransaction();
        } catch (SQLException e) {
            log.error("Failed to commit and stop transaction", e);
            throw new ActionException(e);
        }

        log.info("Book added successfuly!");
        return "redirect:user-cabinet&notification=Book added";
    }
}
