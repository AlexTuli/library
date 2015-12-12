package com.epam.alex.task4.propmanager;

/**
 * Created by AlexTuli on 12/5/15.
 *
 * @author Bocharnikov Alexandr
 */
public class PropertyReaderException extends RuntimeException {

    public PropertyReaderException() {
        super();
    }

    public PropertyReaderException(String message) {
        super(message);
    }

    public PropertyReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyReaderException(Throwable cause) {
        super(cause);
    }
}
