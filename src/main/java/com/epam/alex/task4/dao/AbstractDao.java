package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.AbstractEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Dao. Here I use template pattern
 * Created by AlexTuli on 12/6/15.
 *
 * @author Bocharnikov Alexandr
 */
public abstract class AbstractDao<T extends AbstractEntity> {

    protected Connection connection;

    private DaoFactory factory;

    private PreparedStatement preparedStatement;

    public AbstractDao(Connection connection, DaoFactory factory) {
        this.connection = connection;
        this.factory = factory;
    }

    /**
     * Create a row in a table
     * @param t entity
     */
    public void create(T t){
        try {
            preparedStatement = connection.prepareStatement(getCreateQuery());
            preparedStatement = setFieldsInCreateStatement(preparedStatement, t);
            this.preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Trouble by creating in DAO",e);
        }
    }

    public T read(int id) {
        List<T> result;
        try {
            preparedStatement = connection.prepareStatement(getReadQuery());
            preparedStatement = setFieldsInReadStatement(preparedStatement, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Trouble by reading in DAO",e);
        }
        if (result == null || result.size() == 0) {
            return null;
        }
        if (result.size() > 1) {
            throw new DaoException("Received more than one record.");
        }
        return result.iterator().next();
    }

    public List<T> readAll() {
        List<T> result = new ArrayList<>();
        try {
            PreparedStatement readAll = connection.prepareStatement(getReadAllQuery());
            readAll.execute();
            ResultSet resultSet = readAll.getResultSet();
            result.addAll(parseResultSet(resultSet));

        } catch (SQLException e) {
            throw new DaoException("Trouble in BookDAO.readAll()", e);
        }
        return result;
    }

    public void update(T t) {
        try {
            preparedStatement = connection.prepareStatement(getUpdateQuery());
            preparedStatement = setFieldsInUpdateStatement(preparedStatement, t);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Trouble by updating in DAO",e);
        }
    }

    public void delete(T t) {
        try {
            preparedStatement = connection.prepareStatement(getDeleteQuery());
            preparedStatement = setFieldsInDeleteStatement(preparedStatement, t);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Trouble by deleting in DAO",e);
        }
    }

    protected abstract String getCreateQuery();

    protected abstract PreparedStatement setFieldsInCreateStatement(PreparedStatement statement, T entity);

    protected abstract String getReadQuery();

    protected abstract String getReadAllQuery();

    protected abstract PreparedStatement setFieldsInReadStatement(PreparedStatement statement, int id);

    protected abstract String getUpdateQuery();

    protected abstract PreparedStatement setFieldsInUpdateStatement(PreparedStatement statement, T entity);

    protected abstract String getDeleteQuery();

    protected abstract PreparedStatement setFieldsInDeleteStatement(PreparedStatement statement, T entity);

    protected abstract List<T> parseResultSet(ResultSet resultSet);

    protected final DaoFactory getFactory() {
        return this.factory;
    }


}