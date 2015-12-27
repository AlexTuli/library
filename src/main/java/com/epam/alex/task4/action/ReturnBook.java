package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.dao.SubscriptionDao;
import com.epam.alex.task4.entity.Book;
import com.epam.alex.task4.entity.Subscription;
import com.epam.alex.task4.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/14/15.
 *
 * @author Bocharnikov Alexandr
 */
public class ReturnBook extends AbstractAction {

    private static final Logger log = Logger.getLogger(ReturnBook.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Starting to delete book from subscription");
        String sId = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(sId);
        } catch (NumberFormatException e) {
            log.error("Incorrect book ID", e);
            return "redirect:user-cabinet&notification=Incorrect book ID";
        }
        Book book = new Book();
        book.setId(id);
        Subscription subscription = new Subscription();
        subscription.addBook(book);
        SubscriptionDao subscriptionDao = daoFactory.getDao(SubscriptionDao.class);

        log.debug("Get user from session");
        User user = (User) request.getSession(false).getAttribute("user");
        log.debug("Get subscription by user");
        int idSubscription = subscriptionDao.read(user.getId()).getId();
        subscription.setId(idSubscription);

        log.debug("Start to delete book");

        startTransaction();

        try {
            subscriptionDao.delete(subscription);
        } catch (DaoException e) {
            log.warn("Can't remove book, check ID", e);
            rollback();
            return "redirect:user-cabinet&notification=Can't remove book, check ID";
        }

        commit();

        log.info("Book deleted from subscription successfully!");
        return "redirect:user-cabinet&notification=Book returned!";
    }


}
