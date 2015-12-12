package com.epam.alex.task4.action.redirect;

import com.epam.alex.task4.action.AbstractAction;
import com.epam.alex.task4.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/11/15.
 *
 * @author Bocharnikov Alexandr
 */
public class UserCabinet extends AbstractAction {

    public UserCabinet(DaoFactory factory) {
        super(factory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return request.getContextPath() + "/WEB-INF/user-cabinet.jsp";
    }
}
