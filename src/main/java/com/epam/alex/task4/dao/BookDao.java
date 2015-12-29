package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexTuli on 11/26/15.
 *
 * @author Bocharnikov Alexandr
 */
public class BookDao extends AbstractDao<Book> {

    public static final String CREATE_QUERY = "INSERT INTO BOOK (ID, AUTHOR, TITLE) VALUES (DEFAULT, ?, ?)";
    public static final String READ_BY_ID = "SELECT * FROM BOOK WHERE  ID LIKE ?";
    public static final String READ_ALL = "SELECT * FROM BOOK";
    public static final String UPDATE_QUERY = "UPDATE BOOK SET AUTHOR = ?, TITLE = ? WHERE ID LIKE ?";
    public static final String DELETE_QUERY = "DELETE FROM BOOK WHERE ID LiKE ?";

    public BookDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected PreparedStatement setFieldsInCreateStatement(PreparedStatement statement, Book book) {
        try {
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getTitle());
        } catch (SQLException e) {
            throw new DaoException("Trouble in BookDao by setFieldsInCreateStatement", e);
        }
        return statement;
    }

    @Override
    protected String getReadQuery() {
        return READ_BY_ID;
    }

    @Override
    protected String getReadByEntityQuery() {
        return null;
    }

    @Override
    protected String getReadAllQuery() {
        return READ_ALL;
    }

    @Override
    protected PreparedStatement setFieldsInReadByEntityStatement(PreparedStatement preparedStatement, Book book) {
        return null;
    }

    @Override
    protected PreparedStatement setFieldsInReadStatement(PreparedStatement statement, int id) {
        try {
            statement.setInt(1, id);
        } catch (SQLException e) {
            throw new DaoException("Trouble with creating Read Statement in BookDao", e);
        }
        return statement;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected PreparedStatement setFieldsInUpdateStatement(PreparedStatement statement, Book book) {
        try {
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getTitle());
            statement.setInt(3, book.getId());
        } catch (SQLException e) {
            throw new DaoException("Trouble in BookDao by setFieldInUpdateStatement", e);
        }
        return statement;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected PreparedStatement setFieldsInDeleteStatement(PreparedStatement statement, Book book) {
        try {
            statement.setInt(1, book.getId());
        } catch (SQLException e) {
            throw new DaoException("Trouble in BookDao by setFieldsInDeleteStatement()", e);
        }
        return statement;
    }


    @Override
    protected List<Book> parseResultSet(ResultSet resultSet) {
        List<Book> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Book temp = new Book();
                temp.setAuthor(resultSet.getString("AUTHOR"));
                temp.setTitle(resultSet.getString("TITLE"));
                temp.setId(resultSet.getInt("ID"));
                result.add(temp);
            }
        } catch (SQLException e) {
            throw new DaoException("Trouble in BookDAO parseResultSet(ResultSet)", e);
        }
        return result;
    }

    @Override
    protected int parseGeneratedKeys(ResultSet generatedKeys) {

        return 0;
    }

}
