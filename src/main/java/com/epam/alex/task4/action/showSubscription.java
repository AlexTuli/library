package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.SubscriptionDao;
import com.epam.alex.task4.entity.Subscription;
import com.epam.alex.task4.entity.User;
import com.epam.alex.task4.utilites.Utilities;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/21/15.
 *
 * @author Bocharnikov Alexandr
 */
public class ShowSubscription extends AbstractAction {

    private static final Logger log = Logger.getLogger(ShowSubscription.class);

    /**
     * Show all books in subscription of user
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        SubscriptionDao subscriptionDao = daoFactory.getDao(SubscriptionDao.class);
        User user = Utilities.getUserFromSession(request);

        if (user == null) {
            daoFactory.close();
            log.error("User not in session");
            return null;
        }

        log.debug("Starting to read subscription");
        Subscription subscription;
        try {
            subscription = subscriptionDao.read(user.getId());
            if (subscription != null) {
                log.debug("Subscription read successful! Set to attribute");
                log.info("Subscription books " + subscription.getBookList());
                request.setAttribute("subscription", subscription);
            }
        } catch (DaoException e) {
            log.error("Subscription is empty");
            return "redirect:subscriptions&info=You_have_no_book";
        } finally {
            daoFactory.close();
        }

        return request.getContextPath() + "/WEB-INF/jsp/subscriptions.jsp";
    }
}
