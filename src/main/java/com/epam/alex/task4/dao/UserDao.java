package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    protected String getReadAllQuery() {
        return null;
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
        return null;
    }
}
