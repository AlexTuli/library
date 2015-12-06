package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.AbstractEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Abstract Dao. Here I use template pattern
 * Created by AlexTuli on 12/6/15.
 *
 * @author Bocharnikov Alexandr
 */
public abstract class AbstractDao {

    protected Connection connection;

    private PreparedStatement preparedStatement;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Create a row in table
     * @param entity entity
     */
    public void create(AbstractEntity entity){
        try {
            preparedStatement = connection.prepareStatement(getCreateQuery());
            preparedStatement = setFieldsInCreateStatement(preparedStatement, entity);
            this.preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Trouble by creating in DAO",e);
        }
    }

    public void update(AbstractEntity entity) {
        try {
            preparedStatement = connection.prepareStatement(getUpdateQuery());
            preparedStatement = setFieldsInUpdateStatement(preparedStatement, entity);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Trouble by updating in DAO",e);
        }
    }

    public AbstractEntity read(int id) {
        AbstractEntity result;
        try {
            preparedStatement = connection.prepareStatement(getReadQuery());
            preparedStatement = setFieldsInReadStatement(preparedStatement, id);
            preparedStatement.execute();
            result = getEntity(preparedStatement);
        } catch (SQLException e) {
            throw new DaoException("Trouble by reading in DAO",e);
        }
        return result;

    }

    public void delete(AbstractEntity entity) {
        try {
            preparedStatement = connection.prepareStatement(getDeleteQuery());
            preparedStatement = setFieldsInDeleteStatement(preparedStatement, entity);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Trouble by deleting in DAO",e);
        }
    }

    protected abstract String getUpdateQuery();

    protected abstract String getCreateQuery();

    protected abstract String getDeleteQuery();

    protected abstract String getReadQuery();

    protected abstract AbstractEntity getEntity(PreparedStatement statement);

    protected abstract PreparedStatement setFieldsInDeleteStatement(PreparedStatement preparedStatement, AbstractEntity entity);

    protected abstract PreparedStatement setFieldsInReadStatement(PreparedStatement preparedStatement, int id);

    protected abstract PreparedStatement setFieldsInCreateStatement(PreparedStatement statement, AbstractEntity entity);

    protected abstract PreparedStatement setFieldsInCreateStatement(PreparedStatement statement , int id);

    protected abstract PreparedStatement setFieldsInUpdateStatement(PreparedStatement preparedStatement, AbstractEntity entity);


}