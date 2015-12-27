package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.BookDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.entity.Book;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexTuli on 12/14/15.
 *
 * @author Bocharnikov Alexandr
 */
public class AddBook extends AbstractAction {

    private static final Logger log = Logger.getLogger(AddBook.class);

    /**
     * Add book to library
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //todo check it
        log.info("Starting to add book in library");
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        log.debug("Set field in book complete");

        BookDao bookDao = daoFactory.getDao(BookDao.class);

        startTransaction();

        try {
            log.debug("Create new row in Book table");
            bookDao.create(book);
        } catch (DaoException e) {
            log.error("Can't create new Book", e);
            rollback();
            return "redirect:admin-cabinet&info=Failed to add book";
        }

        commit();
        daoFactory.close();
        log.info("Book added successfully");
        return "redirect:admin-cabinet&info=Book added";
    }


}
