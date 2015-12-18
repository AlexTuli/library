package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.AbstractEntity;
import org.apache.log4j.Logger;

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

    private final static Logger logger = Logger.getLogger(AbstractDao.class);

    protected Connection connection;

    private DaoFactory factory;

    private PreparedStatement preparedStatement;

    public AbstractDao(Connection connection, DaoFactory factory) {
        this.connection = connection;
        this.factory = factory;
    }

    /**
     * Create a row in a table
     *
     * @param t entity
     */
    public T create(T t) throws DaoException {
        try {
            preparedStatement = connection.prepareStatement(getCreateQuery());
            preparedStatement = setFieldsInCreateStatement(preparedStatement, t);
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            logger.debug("Start to parse generated keys");
            int id = parseGeneratedKeys(generatedKeys);
            logger.debug("Key is " + id);
            if (id != 0) {
                logger.debug("Set id to entity");
                t.setId(id);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException("Trouble by creating in DAO", e);
        }
        logger.debug("Return entity");
        return t;
    }

    public T read(int id) throws DaoException {
        List<T> result;
        try {
            logger.debug("Create prepare statement to read by id");
            preparedStatement = connection.prepareStatement(getReadQuery());
            logger.debug("Set fields to prepared statement");
            preparedStatement = setFieldsInReadStatement(preparedStatement, id);
            logger.debug("Executing query");
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Parsing result set");
            result = parseResultSet(resultSet);
            logger.debug("Close result set");
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Trouble by reading in DAO");
            throw new DaoException(e);
        }
        if (result == null || result.size() == 0) {
            logger.error("Record not found");
            throw new DaoException();
        }
        if (result.size() > 1) {
            logger.error("Received more than one record.");
            throw new DaoException();
        }
        logger.info("Return result");
        return result.iterator().next();
    }

    public T read(T t) throws DaoException {
        List<T> result = null;
        try {
            preparedStatement = connection.prepareStatement(getReadByEntityQuery());
            preparedStatement = setFieldsInReadByEntityStatement(preparedStatement, t);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = parseResultSet(resultSet);
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        if (result == null || result.size() == 0) {
            throw new DaoException("Record not found");
        }
        if (result.size() > 1) {
            throw new DaoException("Received more than one record.");
        }

        return result.iterator().next();
    }

    public List<T> readAll() throws DaoException {
        List<T> result = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(getReadAllQuery());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            result.addAll(parseResultSet(resultSet));
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException("Trouble in BookDAO.readAll()", e);
        }
        return result;
    }

    public void update(T t) throws DaoException {
        try {
            preparedStatement = connection.prepareStatement(getUpdateQuery());
            preparedStatement = setFieldsInUpdateStatement(preparedStatement, t);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException("Trouble by updating in DAO", e);
        }
    }

    public void delete(T t) throws DaoException {
        try {
            preparedStatement = connection.prepareStatement(getDeleteQuery());
            preparedStatement = setFieldsInDeleteStatement(preparedStatement, t);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException("Trouble by deleting in DAO", e);
        }
    }

    protected abstract String getCreateQuery();

    protected abstract PreparedStatement setFieldsInCreateStatement(PreparedStatement statement, T entity);

    protected abstract String getReadQuery();

    protected abstract String getReadByEntityQuery();

    protected abstract String getReadAllQuery();

    protected abstract PreparedStatement setFieldsInReadByEntityStatement(PreparedStatement preparedStatement, T t);

    protected abstract PreparedStatement setFieldsInReadStatement(PreparedStatement statement, int id);

    protected abstract String getUpdateQuery();

    protected abstract PreparedStatement setFieldsInUpdateStatement(PreparedStatement statement, T entity);

    protected abstract String getDeleteQuery();

    protected abstract PreparedStatement setFieldsInDeleteStatement(PreparedStatement statement, T entity);

    protected abstract List<T> parseResultSet(ResultSet resultSet);

    protected abstract int parseGeneratedKeys(ResultSet generatedKeys);

    protected final DaoFactory getFactory() {
        return this.factory;
    }
}