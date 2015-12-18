package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.entity.Book;
import com.sun.istack.internal.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by AlexTuli on 12/1/15.
 *
 * @author Bocharnikov Alexandr
 */
public class CheckBooks extends AbstractAction {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CheckBooks.class);

    public CheckBooks(DaoFactory factory) {
        super(factory);
    }

    /**
     * Return all books in library
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Read all books in library");
        AbstractDao book = factory.getDao("book");
        log.debug("BookDao.readAll");
        List<Book> books = book.readAll();
        request.setAttribute("books", books);
        log.debug("Return redirect");
        return request.getContextPath() + "/WEB-INF/check-for-books.jsp";
    }
}
