package com.epam.alex.task4.connection;

/**
 * Created by AlexTuli on 11/27/15.
 *
 * @author Bocharnikov Alexandr
 */
public class ConnectionException extends RuntimeException {

    public ConnectionException() {
        super();
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }
}
