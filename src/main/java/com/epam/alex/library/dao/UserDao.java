package com.epam.alex.library.dao;

import com.epam.alex.library.entity.Role;
import com.epam.alex.library.entity.Subscription;
import com.epam.alex.library.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexTuli on 12/8/15.
 *
 * @author Bocharnikov Alexandr
 */
public class UserDao extends AbstractDao<User> {

    private static final Logger log = Logger.getLogger(UserDao.class);
    public static final String CREATE_QUERY = "INSERT INTO USER (NAME, PASSWORD, ROLE_ID, ID, FIRST_NAME, LAST_NAME) " +
            "VALUES (?, ?, ?, DEFAULT, ?, ?)";
    public static final String READ_BY_ID_QUERY = "SELECT * FROM USER " +
            "INNER JOIN ROLE ON USER.ROLE_ID = ROLE.ID " +
            "INNER JOIN SUBSCRIPTION ON USER.ID = SUBSCRIPTION.USER_ID " +
            "WHERE USER.ID LIKE ?";
    public static final String READ_BY_LOGIN_PASSWORD = "SELECT * FROM USER " +
            "INNER JOIN ROLE ON USER.ROLE_ID = ROLE.ID\n" +
            "INNER JOIN SUBSCRIPTION ON USER.ID = SUBSCRIPTION.USER_ID\n" +
            "WHERE NAME LIKE ? AND PASSWORD LIKE ?";
    public static final String READ_ALL_USERS_QUERY = "SELECT * FROM USER\n" +
            "INNER JOIN ROLE ON USER.ROLE_ID = ROLE.ID\n" +
            "INNER JOIN SUBSCRIPTION ON USER.ID = SUBSCRIPTION.USER_ID";
    public static final String UPDATE_QUERY = "UPDATE USER SET NAME = ?, ROLE_ID = ? WHERE ID LIKE ?";
    public static final String DELETE_QUERY = "DELETE FROM USER WHERE ID LIKE ?";

    public UserDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected PreparedStatement setFieldsInCreateStatement(PreparedStatement statement, User user) {
        try {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().getId());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
        } catch (SQLException e) {
            log.error("Can not set fields in create statement");
            throw new DaoException(e);
        }
        return statement;
    }

    @Override
    protected String getReadQuery() {
        return READ_BY_ID_QUERY;
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
        return READ_BY_LOGIN_PASSWORD;
    }

    @Override
    protected String getReadAllQuery() {
        return READ_ALL_USERS_QUERY;
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
        return UPDATE_QUERY;
    }

    @Override
    protected PreparedStatement setFieldsInUpdateStatement(PreparedStatement statement, User user) {
        try {
            log.debug("Set name " + user.getLogin());
            statement.setString(1, user.getLogin());
            log.debug("Set role" + user.getRole().getName());
            statement.setInt(2, user.getRole().getId());
            log.debug("In user with ID " + user.getId());
            statement.setInt(3, user.getId());
        } catch (SQLException e) {
            log.error("Can't set fields in update statement UserDAO");
            throw new DaoException(e);
        }
        return statement;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected PreparedStatement setFieldsInDeleteStatement(PreparedStatement statement, User user) {
        try {
            log.debug("Setting fields in delete statement");
            statement.setInt(1, user.getId());
        } catch (SQLException e) {
            log.error("Trouble with setting fields in delete statement", e);
            throw new DaoException(e);
        }
        return statement;
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) {
        List<User> users = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            log.debug("Result set have " + metaData.getColumnCount() + " columns");
        } catch (SQLException ignored) {
        }
        try {
            while (resultSet.next()) {
                log.debug("Create new user");
                User user = new User();
                log.debug("Set id to user");
                user.setId(resultSet.getInt("ID"));
                log.debug("Set login to user");
                user.setLogin(resultSet.getString("NAME"));
                log.debug("Set role to Role");
                String roleString = resultSet.getString("ROLE");
                log.debug("Set role to user");
                Subscription subscription = new Subscription();
                log.debug("Set ID to subscription");
                subscription.setId(resultSet.getInt(9));
                log.debug("Set subscription to user");
                user.setSubscription(subscription);
                user.setFirstName(resultSet.getString("FIRST_NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                if (roleString.equalsIgnoreCase("ADMINISTRATOR")) user.setRole(Role.getAdminRole());
                else if (roleString.equalsIgnoreCase("USER")) user.setRole(Role.getUserRole());
                else throw new DaoException("User have no role");

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
            log.error("Can't parse generated keys");
            throw new DaoException(e);
        }
        log.debug("Return ID");
        return id;
    }
}
