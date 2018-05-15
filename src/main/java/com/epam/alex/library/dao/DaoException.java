package com.epam.alex.library.dao;

/**
 * Created by AlexTuli on 11/26/15.
 *
 * @author Bocharnikov Alexandr
 */
public class DaoException extends RuntimeException {

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Exception e) {
        super(e);
    }


}
