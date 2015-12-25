package com.epam.alex.task4.action.redirect;

import com.epam.alex.task4.action.AbstractAction;
import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.entity.Subscription;
import com.epam.alex.task4.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by AlexTuli on 12/21/15.
 *
 * @author Bocharnikov Alexandr
 */
public class RedirectToSubcription extends AbstractAction {

    private static final Logger log = Logger.getLogger(RedirectToSubcription.class);

    public RedirectToSubcription(DaoFactory factory) {
        super(factory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AbstractDao subscriptionDao = daoFactory.getDao("subscription");
        User user = null;

        log.debug("Starting to read subscription");
        HttpSession session = request.getSession(false);
        try {
            user = (User) session.getAttribute("user");
            log.debug("User get from session " + user.getLogin());
        } catch (DaoException e) {
            log.error("Can't read user from session :(", e);
        }
        if (user != null) {
            log.debug("Starting to read subscription");
            Subscription subscription;
            try {
                subscription = (Subscription) subscriptionDao.read(user.getId());
                if (subscription != null) {
                    log.debug("Subscription read successful! Set to attribute");
                    log.info("Subscription books " + subscription.getBookList());
                    request.setAttribute("subscription", subscription);
                }
            } catch (DaoException e) {
                log.debug("Subscription is empty");
            }
        } else {
            log.error("User is null, can't read subscription");
        }
        return request.getContextPath() + "/WEB-INF/subscriptions.jsp";
    }
}
