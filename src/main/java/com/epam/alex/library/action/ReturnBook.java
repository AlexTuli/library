package com.epam.alex.library.action;

import com.epam.alex.library.dao.DaoException;
import com.epam.alex.library.dao.SubscriptionDao;
import com.epam.alex.library.entity.Book;
import com.epam.alex.library.entity.Subscription;
import com.epam.alex.library.entity.User;
import com.epam.alex.library.utilites.Utilities;
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

    /**
     * Delete book from subscription of user
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Starting to delete book from subscription");
        int id = Utilities.getId(request);
        if (id < 1) {
            daoFactory.close();
            return "redirect::return-book&info=Wrong_ID_format";
        }

        Book book = new Book();
        book.setId(id);
        Subscription subscription = new Subscription();
        subscription.addBook(book);
        SubscriptionDao subscriptionDao = daoFactory.getDao(SubscriptionDao.class);

        log.debug("Get user from session");
        User user = Utilities.getUserFromSession(request);
        if (user == null) {
            daoFactory.close();
            return "redirect:index&info=You're_not_logged";
        }
        log.debug("Get subscription by user");
        int idSubscription = subscriptionDao.read(user.getId()).getId();
        subscription.setId(idSubscription);
        log.debug("Start to delete book");

        startTransaction();
        int delete;

        try {
            delete = subscriptionDao.delete(subscription);
        } catch (DaoException e) {
            log.warn("Can't remove book, check ID", e);
            rollback();
            daoFactory.close();
            return "redirect:return-book&info=Can't_remove_book,_check_ID";
        }
        if (delete == 0) {
            log.error("User not found, can't delete");
            rollback();
            daoFactory.close();
            return "redirect:return-book&info=Book_not_found";
        }
        commit();
        daoFactory.close();
        log.info("Book deleted from subscription successfully!");
        return "redirect:user-cabinet&notification=Book_returned!";
    }


}
