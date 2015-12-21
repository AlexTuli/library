package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.DaoFactory;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/21/15.
 *
 * @author Bocharnikov Alexandr
 */
public class PromoteUser extends AbstractAction {

    public PromoteUser(DaoFactory factory) {
        super(factory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        throw new RuntimeException("Not implement yet");

    }
}
