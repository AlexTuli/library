package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.AbstractEntity;
import com.epam.alex.task4.entity.Role;
import com.epam.alex.task4.entity.User;

import javax.jws.soap.SOAPBinding;
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

    public UserDao(Connection connection, DaoFactory factory) {
        super(connection, factory);
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO";
    }

    @Override
    protected PreparedStatement setFieldsInCreateStatement(PreparedStatement statement, User entity) {
        return null;
    }

    @Override
    protected String getReadQuery() {
        return null;
    }

    @Override
    protected String getReadByEntityQuery() {
        return "SELECT * FROM USER WHERE NAME LIKE ? AND PASSWORD LIKE ?";
    }

    @Override
    protected String getReadAllQuery() {
        return null;
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
    protected PreparedStatement setFieldsInReadStatement(PreparedStatement statement, int id) {
        return null;
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
                User user = new User();
                user.setId(resultSet.getInt("ID"));
                user.setLogin(resultSet.getString("NAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
                int roleId = resultSet.getInt("ROLE_ID");
                AbstractDao roleDao = getFactory().getDao("role");
                Role role = (Role) roleDao.read(roleId);
                user.setRole(role);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Trouble by parsing resultSet in UserDao");
        }
        return users;
    }
}
