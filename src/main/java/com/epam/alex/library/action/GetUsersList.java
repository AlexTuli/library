package com.epam.alex.library.action;

import com.epam.alex.library.dao.DaoException;
import com.epam.alex.library.dao.UserDao;
import com.epam.alex.library.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by AlexTuli on 12/18/15.
 *
 * @author Bocharnikov Alexandr
 */
public class GetUsersList extends AbstractAction {

    private static final Logger log = LoggerFactory.getLogger(GetUsersList.class);


    /**
     * Read all users from DB
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Start to reading users");
        UserDao userDao = daoFactory.getDao(UserDao.class);
        List<User> userList;
        try {
            log.debug("userDao.readAll() in GetUserList");
            userList = userDao.readAll();
        } catch (DaoException e) {
            log.debug("Can't read users");
            daoFactory.close();
            return "redirect:admin-cabinet&info=Failed to read users";
        }
        request.setAttribute("users", userList);
        daoFactory.close();
        log.info("Read successful");
        return request.getContextPath() + "/WEB-INF/jsp/get-users-list.jsp";
    }
}
