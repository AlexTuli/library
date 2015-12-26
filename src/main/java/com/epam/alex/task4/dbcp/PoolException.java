package com.epam.alex.task4.dbcp;

/**
 * Created by AlexTuli on 11/27/15.
 *
 * @author Bocharnikov Alexandr
 */
public class PoolException extends RuntimeException {

    public PoolException() {
        super();
    }

    public PoolException(String message) {
        super(message);
    }

    public PoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolException(Throwable cause) {
        super(cause);
    }
}
