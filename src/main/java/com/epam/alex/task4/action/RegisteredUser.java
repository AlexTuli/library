package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.SubscriptionDao;
import com.epam.alex.task4.dao.UserDao;
import com.epam.alex.task4.entity.Role;
import com.epam.alex.task4.entity.Subscription;
import com.epam.alex.task4.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AlexTuli on 12/12/15.
 *
 * @author Bocharnikov Alexandr
 */
public class RegisteredUser extends AbstractAction {

    private final static Logger logger = Logger.getLogger(RegisteredUser.class);
    public static final String WORD_REGEX = "\\w+";


    /**
     * Create new row with new User in DB
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        //TODO add md5 encrypt

        logger.debug("Check values");
        if (!isValidName(login)) {
            return "redirect:registration&info=Incorrect_login";
        }
        if (isEmpty(password)) {
            return "redirect:registration&info=Enter_your_password";
        }
        if (password.length() < 5) {
            return "redirect:registration&info=Password_too_short";
        }
        if (!isValidName(firstName)) {
            return "redirect:registration&info=Incorrect_first_name";
        }
        if (!isValidName(lastName)) {
            return "redirect:registration&info=Incorrect_last_name";
        }

        logger.debug("Create user and set fields");
        User user = new User();
        user.setLogin(login.toLowerCase());
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(Role.getUserRole());
        Subscription subscription = new Subscription();
        user.setSubscription(subscription);

        logger.debug("Get user dao");
        UserDao userDao = daoFactory.getDao(UserDao.class);
        try {
            startTransaction();
            user = userDao.create(user);
        } catch (DaoException e) {
            rollback();
            return "redirect:registration&info=Can't_create_user";
        }

        subscription.setUser(user);
        logger.debug("Get subscription dao");
        SubscriptionDao subscriptionDao = daoFactory.getDao(SubscriptionDao.class);
        try {
            logger.debug("Start to create subscription");
            subscriptionDao.create(subscription);
        } catch (DaoException e) {
            rollback();
            return "redirect:registration&info=Can't_create_subscription";
        }

        commit();
        logger.debug("User successful created");
        return "redirect:index&info=Registration_successful!";
    }

    /**
     * Check string for null or empty
     *
     * @param string Param to check
     * @return true or false
     */
    private boolean isEmpty(String string) {
        if (string == null || string.isEmpty()) {
            daoFactory.close();
            logger.error("Empty value");
            return true;
        } else return false;
    }

    /**
     * Check Name or Login to match requirements
     *
     * @param string String to check
     * @return true or false
     */
    private boolean isValidName(String string) {
        if (isEmpty(string)) return false;
        if (string.length() > 60) {
            logger.error("Too long");
            return false;
        }
        Pattern pattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
}
