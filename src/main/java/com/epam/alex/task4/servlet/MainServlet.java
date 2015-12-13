package com.epam.alex.task4.servlet;

import com.epam.alex.task4.action.Action;
import com.epam.alex.task4.action.ActionFactory;
import org.apache.log4j.Logger;

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


    private final static Logger logger = Logger.getLogger(MainServlet.class);
    private static final String PARAMETER_ACTION = "action";
    private ActionFactory factory;

    @Override
    public void init() throws ServletException {
        factory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        logger.debug("Start process request");
        String actionName = getActionName(request);
        logger.debug("Get Action " + actionName);
        Action action = factory.getAction(actionName);
        logger.debug("Action" + action + "start");
        String view = action.execute(request, response);
        if (view == null) {
            request.getRequestDispatcher(getServletContext().getContextPath() + "/index.jsp").forward(request, response);
            return;
        }
        if (view.startsWith("redirect:")) {
            logger.debug("Redirecting");
            response.sendRedirect(getRedirectLocation(view));
            return;
        }
        logger.debug("forwarding");
        request.getRequestDispatcher(view).forward(request, response);
    }

    private String getRedirectLocation(String view) {
        return getServletContext().getContextPath() + "/controller?action=" + view.substring(9);
    }

    private String getActionName(HttpServletRequest request) {
        return request.getParameter(PARAMETER_ACTION);
    }


    //
//    public final static Logger logger = Logger.getLogger(MainServlet.class);
//    public static final String PARAMETER_ACTION = "action";
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        processRequest(request, response);
//
//    }
//
//    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String action = request.getParameter(PARAMETER_ACTION);
//        SubscriptionDao subscriptionDao;
//        BookDao bookDao;
//        switch (action) {
//            case "authorize" :
//                Action action3 = new Authorize();
//                action3.execute(request, response);
//                return;
//            case "get-subscription":
//                subscriptionDao = new SubscriptionDao();
//                Subscription alexTuli = subscriptionDao.read(new User("AlexTuli", 1));
//                request.setAttribute("book", alexTuli.getBookList());
//                break;
//            case "add-book-to-subscription":
//                subscriptionDao = new SubscriptionDao();
//
//                int id = Integer.parseInt(request.getParameter("id"));
//                bookDao = new BookDao();
//                Book book = bookDao.read(id);
//                Subscription alexTuli1 = subscriptionDao.read(new User("AlexTuli", 1));
//                alexTuli1.addBook(book);
//                subscriptionDao.update(alexTuli1);
//                break;
//            case "check-books":
//                Action check = ActionFactory.getAction(action);
//                check.execute(request, response);
//                return;
//            case "delete-book":
//                subscriptionDao = new SubscriptionDao();
//                int idDelete = Integer.parseInt(request.getParameter("id"));
//                request.getSession();
//                //boolean isSuccessful= subscriptionDao.remove();
//                //if (isSuccessful) request.setAttribute("message", "Book is deleted");
//                request.setAttribute("message", "Book is not deleted");
//                break;
//            case "get-notification":
//                NotificationDao notificationDao = new NotificationDao();
//                List<Notification> result = notificationDao.get(new User("AlexTuli", 1));
////                notificationDao.remove(result);
//                request.setAttribute("message", result);
//        }
//
//        request.getRequestDispatcher("/user-cabinet.jsp").forward(request, response);
//    }
}
