package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.entity.Book;

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


    public CheckBooks(DaoFactory factory) {
        super(factory);
    }

    /**
     * Return all books in library
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AbstractDao book = factory.getDao("book");
        List<Book> books = book.readAll();
        request.setAttribute("books", books);
        try {
            request.getRequestDispatcher("/look-for-books.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new ActionException("Exception in process of checking books", e);
        }
        return null;
    }
}
