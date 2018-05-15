package com.epam.alex.library.action;

import com.epam.alex.library.dao.BookDao;
import com.epam.alex.library.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by AlexTuli on 12/1/15.
 *
 * @author Bocharnikov Alexandr
 */
public class CheckBooks extends AbstractAction {

    private static final Logger log = LoggerFactory.getLogger(CheckBooks.class);


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
