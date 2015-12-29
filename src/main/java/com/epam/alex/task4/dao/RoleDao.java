package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexTuli on 12/11/15.
 *
 * @author Bocharnikov Alexandr
 */
public class RoleDao extends AbstractDao<Role> {

    public static final String READ_BY_ID_QUERY = "SELECT * FROM ROLE WHERE ID LIKE ?";
    public static final String READ_ALL_QUERY = "SELECT * FROM ROLE";

    public RoleDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getCreateQuery() {
        return null;
    }

    @Override
    protected PreparedStatement setFieldsInCreateStatement(PreparedStatement statement, Role entity) {
        return null;
    }

    @Override
    protected String getReadQuery() {

        return READ_BY_ID_QUERY;

    }

    @Override
    protected String getReadByEntityQuery() {
        return null;
    }

    @Override
    protected String getReadAllQuery() {

        return READ_ALL_QUERY;
    }

    @Override
    protected PreparedStatement setFieldsInReadByEntityStatement(PreparedStatement preparedStatement, Role role) {
        return null;
    }

    @Override
    protected PreparedStatement setFieldsInReadStatement(PreparedStatement statement, int id) {
        try {
            statement.setInt(1, id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return statement;
    }

    @Override
    protected String getUpdateQuery() {
        return null;
    }

    @Override
    protected PreparedStatement setFieldsInUpdateStatement(PreparedStatement statement, Role entity) {
        return null;
    }

    @Override
    protected String getDeleteQuery() {
        return null;
    }

    @Override
    protected PreparedStatement setFieldsInDeleteStatement(PreparedStatement statement, Role entity) {
        return null;
    }

    @Override
    protected List<Role> parseResultSet(ResultSet resultSet) {
        List<Role> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Role role = new Role();
                role.setName(resultSet.getString("ROLE"));
                role.setId(resultSet.getInt("ID"));
                result.add(role);
            }
        } catch (SQLException e) {
            throw new DaoException("Trouble by parsing result in RoleDAO", e);
        }
        return result;
    }

    @Override
    protected int parseGeneratedKeys(ResultSet generatedKeys) {

        return 0;
    }
}
