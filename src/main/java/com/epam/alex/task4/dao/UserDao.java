package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.Role;
import com.epam.alex.task4.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexTuli on 12/8/15.
 *
 * @author Bocharnikov Alexandr
 */
public class UserDao extends AbstractDao<User> {

    private static final Logger log = Logger.getLogger(UserDao.class);

    public UserDao(Connection connection, DaoFactory factory) {
        super(connection, factory);
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO USER (NAME, PASSWORD, ROLE_ID, ID) VALUES (?, ?, ?, DEFAULT)";
    }

    @Override
    protected PreparedStatement setFieldsInCreateStatement(PreparedStatement statement, User user) {
        try {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().getId());
        } catch (SQLException e) {
            throw new DaoException("Can not set fields in create statement", e);
        }
        return statement;
    }

    @Override
    protected String getReadQuery() {
        return "SELECT * FROM USER INNER JOIN ROLE ON USER.ROLE_ID = ROLE.ID WHERE USER.ID LIKE ?";
    }

    @Override
    protected PreparedStatement setFieldsInReadStatement(PreparedStatement statement, int id) {
        try {
            statement.setInt(1, id);
        } catch (SQLException e) {
            log.error("Trouble by set fields in read by id statement");
            throw new DaoException(e);
        }
        return statement;
    }

    @Override
    protected String getReadByEntityQuery() {
        return "SELECT * FROM USER INNER JOIN ROLE ON USER.ROLE_ID = ROLE.ID\n" +
                "WHERE NAME LIKE ? AND PASSWORD LIKE ?\n";
//                "SELECT * FROM USER WHERE NAME LIKE ? AND PASSWORD LIKE ?";
    }

    @Override
    protected String getReadAllQuery() {
        return "SELECT PASSWORD, NAME, USER.ID, ROLE FROM USER\n" +
                "INNER JOIN ROLE ON USER.ROLE_ID = ROLE.ID;";
    }

    @Override
    protected PreparedStatement setFieldsInReadByEntityStatement(PreparedStatement statement, User user) {

        try {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
        } catch (SQLException e) {
            throw new DaoException("Trouble by set Fields In Read By Entity Statement in UserDao", e);
        }
        return statement;
    }

    @Override
    protected String getUpdateQuery() {
        return null;
    }

    @Override
    protected PreparedStatement setFieldsInUpdateStatement(PreparedStatement statement, User entity) {
        return null;
    }

    @Override
    protected String getDeleteQuery() {
        return null;
    }

    @Override
    protected PreparedStatement setFieldsInDeleteStatement(PreparedStatement statement, User entity) {
        return null;
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) {
        List<User> users = new ArrayList<>();
        try {
            while (resultSet.next()) {
                log.debug("Create new user");
                User user = new User();
                log.debug("Set id to user");
                user.setId(resultSet.getInt("ID"));
                log.debug("Set login to user");
                user.setLogin(resultSet.getString("NAME"));
                log.debug("Create new role");
                Role role = new Role();
                log.debug("Set role to Role");
                role.setRole(resultSet.getString("ROLE"));
                log.debug("Set role to User");
                user.setRole(role);
                log.debug("Add user to collection");
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("Trouble by parsing resultSet in UserDao");
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    protected int parseGeneratedKeys(ResultSet generatedKeys) {
        log.debug("Inside parseGeneratedKeys");
        int id = 0;
        try {
            while (generatedKeys.next()) {
                log.debug("Try to get ID");
                id = generatedKeys.getInt(1);
                log.debug("ID is " + id);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't parse generated keys", e);
        }
        log.debug("Return ID");
        return id;
    }
}
