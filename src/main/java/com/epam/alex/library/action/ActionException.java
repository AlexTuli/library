package com.epam.alex.library.action;

/**
 * Created by AlexTuli on 12/1/15.
 *
 * @author Bocharnikov Alexandr
 */
public class ActionException extends RuntimeException {

    public ActionException() {
        super();
    }

    public ActionException(String message) {
        super(message);
    }

    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionException(Throwable cause) {
        super(cause);
    }
}
