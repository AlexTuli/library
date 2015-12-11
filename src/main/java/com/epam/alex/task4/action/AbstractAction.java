package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/11/15.
 *
 * @author Bocharnikov Alexandr
 */
public abstract class AbstractAction implements Action {

    protected DaoFactory factory;

    public AbstractAction(DaoFactory factory) {
        this.factory = factory;
    }

    public abstract String execute(HttpServletRequest request, HttpServletResponse response);


}
