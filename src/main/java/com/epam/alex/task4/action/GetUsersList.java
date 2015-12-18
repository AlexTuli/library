package com.epam.alex.task4.action;

import com.epam.alex.task4.action.AbstractAction;
import com.epam.alex.task4.action.Action;
import com.epam.alex.task4.dao.AbstractDao;
import com.epam.alex.task4.dao.DaoException;
import com.epam.alex.task4.dao.DaoFactory;
import com.epam.alex.task4.entity.User;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by AlexTuli on 12/18/15.
 *
 * @author Bocharnikov Alexandr
 */
public class GetUsersList extends AbstractAction{

    private static final Logger log = Logger.getLogger(GetUsersList.class);


    public GetUsersList(DaoFactory factory) {
        super(factory);
    }

    /**
     * Read all users from DB
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Start to reading users");
        AbstractDao userDao = factory.getDao("user");
        List<User> userList;
        try {
            log.debug("userDao.readall() in GetUserList");
            userList = userDao.readAll();
        } catch (DaoException e) {
            log.debug("Can't read users", e);
            return "redirect:admin-cabinet&info=Failed to read users";
        }
        request.setAttribute("users", userList);
        log.info("Read successful");
        return request.getContextPath() + "/WEB-INF/get-users-list.jsp";
    }
}
