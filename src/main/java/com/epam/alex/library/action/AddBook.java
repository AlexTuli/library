package com.epam.alex.library.action;

import com.epam.alex.library.dao.BookDao;
import com.epam.alex.library.dao.DaoException;
import com.epam.alex.library.entity.Book;
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
     * Add new book to library
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

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
            return "redirect:add-book&info=Failed_to_add_book";
        }

        commit();
        log.info("Book added successfully");
        return "redirect:admin-cabinet&info=Book_added";
    }


}
