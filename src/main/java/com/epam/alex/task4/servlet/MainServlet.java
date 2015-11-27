package com.epam.alex.task4.servlet;

import com.epam.alex.task4.dao.BookDao;
import com.epam.alex.task4.dao.NotificationDao;
import com.epam.alex.task4.dao.SubscriptionDao;
import com.epam.alex.task4.entity.Book;
import com.epam.alex.task4.entity.Notification;
import com.epam.alex.task4.entity.Subscription;
import com.epam.alex.task4.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by AlexTuli on 11/22/15.
 *
 * @author Bocharnikov Alexandr
 */

@WebServlet(name = "MainServlet", urlPatterns = "/controller")
public class MainServlet extends HttpServlet {

    public final static Logger logger = Logger.getLogger(MainServlet.class);
    public static final String PARAMETER_ACTION = "action";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter(PARAMETER_ACTION);
        SubscriptionDao subscriptionDao;
        BookDao bookDao;

        switch (action) {
            case "get-subscription":
                subscriptionDao = new SubscriptionDao();
                //TODO CHANGE FROM ALEXTULI TO %USERNAME%
                Subscription alexTuli = subscriptionDao.read(new User("AlexTuli", 1));
                request.setAttribute("book", alexTuli.getBookList());
                break;
            case "add-book-to-subscription":
                subscriptionDao = new SubscriptionDao();

                //TODO ADD VALIDATION
                int id = Integer.parseInt(request.getParameter("id"));
                bookDao = new BookDao();
                Book book = bookDao.read(id);
                Subscription alexTuli1 = subscriptionDao.read(new User("AlexTuli", 1));
                alexTuli1.addBook(book);
                subscriptionDao.update(alexTuli1);
                break;
            case "check-books":
                bookDao = new BookDao();
                List<Book> books = bookDao.readAll();
                request.setAttribute("books", books);
                break;
            case "delete-book":
                subscriptionDao = new SubscriptionDao();
                int idDelete = Integer.parseInt(request.getParameter("id"));
                request.getSession();
                // TODO THINK ABOUT HOW TO GET USER_ID OR GET USER_SUBSCRIPTION
                //boolean isSuccessful= subscriptionDao.remove();  TODO FIX IT
                //if (isSuccessful) request.setAttribute("message", "Book is deleted");
                request.setAttribute("message", "Book is not deleted");
                break;
            case "get-notification":
                NotificationDao notificationDao = new NotificationDao();
                List<Notification> result = notificationDao.get(new User("AlexTuli", 1));
//                notificationDao.remove(result);
                request.setAttribute("message", result);
        }

        request.getRequestDispatcher("/user-index.jsp").forward(request, response);
    }
}
