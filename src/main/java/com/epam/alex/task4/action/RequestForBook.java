package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/13/15.
 *
 * @author Bocharnikov Alexandr
 */
public class RequestForBook extends AbstractAction {

    public RequestForBook(DaoFactory factory) {
        super(factory);
    }

    /**
     * Request for a new book and add it to subscription
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return null;
    }
}
