package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.BookDao;
import com.epam.alex.task4.entity.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by AlexTuli on 12/1/15.
 *
 * @author Bocharnikov Alexandr
 */
public class CheckBooks extends AbstractAction {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CheckBooks.class);


    /**
     * Return all books in library
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Read all books in library");
        BookDao book = daoFactory.getDao(BookDao.class);
        log.debug("BookDao.readAll");
        List<Book> books = book.readAll();
        request.setAttribute("books", books);
        log.debug("Return redirect");
        daoFactory.close();
        return request.getContextPath() + "/WEB-INF/jsp/check-for-books.jsp";
    }
}
