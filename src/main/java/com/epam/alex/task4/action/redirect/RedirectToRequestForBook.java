package com.epam.alex.task4.action.redirect;

import com.epam.alex.task4.action.AbstractAction;
import com.epam.alex.task4.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/13/15.
 *
 * @author Bocharnikov Alexandr
 */
public class RedirectToRequestForBook extends AbstractAction {

    public RedirectToRequestForBook(DaoFactory factory) {
        super(factory);
    }

    /**
     * Redirect to requesting for a book
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return request.getContextPath() + "/WEB-INF/insert-book-to-subscription.jsp";
    }
}
