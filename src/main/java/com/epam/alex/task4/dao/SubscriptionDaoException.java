package com.epam.alex.task4.dao;

/**
 * Created by AlexTuli on 11/26/15.
 *
 * @author Bocharnikov Alexandr
 */
public class SubscriptionDaoException extends RuntimeException {

    public SubscriptionDaoException() {
        super();
    }

    public SubscriptionDaoException(String message) {
        super(message);
    }

    public SubscriptionDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriptionDaoException(Exception e) {
        super(e);
    }


}
