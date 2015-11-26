package com.epam.alex.task4.servlet;

import com.epam.alex.task4.dao.SubscriptionDao;
import com.epam.alex.task4.entity.Subscription;
import com.epam.alex.task4.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter(PARAMETER_ACTION);

        switch (action) {
            case "get-subscription" :
                SubscriptionDao subscriptionDao = new SubscriptionDao();
                Subscription alexTuli = subscriptionDao.read(new User("AlexTuli", 1));
                request.setAttribute("book", alexTuli.getBookList());
                break;
        }

        request.getRequestDispatcher("/user-index.jsp").forward(request, response);

    }
}
