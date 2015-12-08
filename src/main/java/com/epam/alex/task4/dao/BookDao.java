package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexTuli on 11/26/15.
 *
 * @author Bocharnikov Alexandr
 */
public class BookDao extends AbstractDao<Book>{

    public BookDao(Connection connection, DaoFactory factory) {
        super(connection, factory);
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO BOOK (ID, AUTHOR, TITLE) VALUES (DEFAULT, ?, ?)";
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
        return "SELECT * FROM BOOK WHERE  ID LIKE ?";
    }

    @Override
    protected String getReadAllQuery() {
        return "SELECT * FROM BOOK";
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
        return "UPDATE BOOK SET AUTHOR = ?, TITLE = ? WHERE ID LIKE ?";
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
        return "DELETE FROM BOOK WHERE ID LiKE ?";
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
            while (resultSet.next()){
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

}
