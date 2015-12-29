package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Not implement yet
 * Created by AlexTuli on 11/26/15.
 *
 * @author Bocharnikov Alexandr
 */
public class NotificationDao extends AbstractDao<Notification> {

    public static final String CREATE_QUERY = "INSERT INTO NOTIFICATION (ID, USER_ID, TEXT) VALUES (DEFAULT, ?, ?)";
    public static final String READ_QUERY = "SELECT TEXT FROM NOTIFICATION WHERE USER_ID LIKE ?";
    public static final String READ_ALL_QUERY = "SELECT TEXT, USER_ID FROM NOTIFICATION";
    public static final String UPDATE_QUERY = "UPDATE NOTIFICATION SET TEXT = ? WHERE ID = ?";
    public static final String DELETE_QUERY = "DELETE FROM NOTIFICATION WHERE ID LIKE ?";

    public NotificationDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected PreparedStatement setFieldsInCreateStatement(PreparedStatement statement, Notification entity) {
        try {
            statement.setInt(1, entity.getUser().getId());
            statement.setString(2, entity.getText());
        } catch (SQLException e) {
            throw new DaoException("Trouble in NotificationDAO by setFieldsInCreateStatement", e);
        }

        return statement;
    }

    @Override
    protected String getReadQuery() {
        return READ_QUERY;
    }

    @Override
    protected String getReadByEntityQuery() {
        throw new DaoException("Not implemented");
    }

    /**
     * @param id USER_ID NOT NOTIFICATION ID
     */
    @Override
    protected PreparedStatement setFieldsInReadStatement(PreparedStatement statement, int id) {
        try {
            statement.setInt(1, id);
        } catch (SQLException e) {
            throw new DaoException("Trouble in NotificationDAO by setFieldsInReadStatement", e);
        }
        return statement;
    }

    @Override
    protected String getReadAllQuery() {
        return READ_ALL_QUERY;
    }

    @Override
    protected PreparedStatement setFieldsInReadByEntityStatement(PreparedStatement preparedStatement, Notification notification) {
        throw new DaoException("Not implemented");
    }

    @Override
    protected List<Notification> parseResultSet(ResultSet resultSet) {
        List<Notification> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Notification temp = new Notification();
                temp.setText(resultSet.getString("TEXT"));
                result.add(temp);
            }
        } catch (SQLException e) {
            throw new DaoException("Trouble in NotificationDAO by parseResultSet", e);
        }
        return result;
    }

    /**
     * Don't implemented
     */
    @Override
    protected int parseGeneratedKeys(ResultSet generatedKeys) {
        return 0;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected PreparedStatement setFieldsInUpdateStatement(PreparedStatement statement, Notification entity) {
        try {
            statement.setString(1, entity.getText());
            statement.setInt(2, entity.getId());
        } catch (SQLException e) {
            throw new DaoException("Trouble in NotificationDAO by setFieldsInUpdateStatement", e);
        }
        return statement;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected PreparedStatement setFieldsInDeleteStatement(PreparedStatement statement, Notification entity) {
        try {
            statement.setInt(1, entity.getId());
        } catch (SQLException e) {
            throw new DaoException("Trouble in NotificationDAO by setFieldsInDeleteStatement", e);
        }
        return statement;
    }
}
