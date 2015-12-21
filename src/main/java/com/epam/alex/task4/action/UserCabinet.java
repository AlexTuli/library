package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.entity.Subscription;
import com.epam.alex.task4.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by AlexTuli on 12/11/15.
 *
 * @author Bocharnikov Alexandr
 */
public class UserCabinet extends AbstractAction {

    private static final Logger log = Logger.getLogger(UserCabinet.class);

    public UserCabinet(DaoFactory factory) {
        super(factory);
    }

    /**
     * Redirect to user cabinet and request for notification
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Authorize complete!");
        return request.getContextPath() + "/WEB-INF/user-cabinet.jsp";
    }
}
