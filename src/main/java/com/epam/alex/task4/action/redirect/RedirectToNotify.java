package com.epam.alex.task4.action.redirect;

import com.epam.alex.task4.action.AbstractAction;
import com.epam.alex.task4.action.Action;
import com.epam.alex.task4.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/18/15.
 *
 * @author Bocharnikov Alexandr
 */
public class RedirectToNotify extends AbstractAction {

    public RedirectToNotify(DaoFactory factory) {
        super(factory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return request.getContextPath() + "/WEB-INF/notify-user.jsp";
    }
}
