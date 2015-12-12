package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/12/15.
 *
 * @author Bocharnikov Alexandr
 */
public class CreateSubscription extends AbstractAction {

    public CreateSubscription(DaoFactory factory) {
        super(factory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.getParameter("");

        return null;
    }
}
