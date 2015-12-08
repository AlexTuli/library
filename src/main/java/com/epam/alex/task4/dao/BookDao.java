package com.epam.alex.task4.dao;

import com.epam.alex.task4.dbcp.ConnectionPool;
import com.epam.alex.task4.entity.AbstractEntity;
import com.epam.alex.task4.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexTuli on 11/26/15.
 *
 * @author Bocharnikov Alexandr
 */
public class BookDao extends AbstractDao{

    public static final String SELECT_ALL_BOOKS = "SELECT * FROM BOOK";

    public BookDao(Connection connection) {
        super(connection);
    }

    //TODO What do with this special method? Add to abstract, or stay here?
    public List<Book> readAll() {
        List<Book> result = new ArrayList<>();
        try {
            PreparedStatement readAll = connection.prepareStatement(SELECT_ALL_BOOKS);
            readAll.execute();
            ResultSet resultSet = readAll.getResultSet();
            while (resultSet.next()) {
                Book temp = new Book();
                temp.setAuthor(resultSet.getString("AUTHOR"));
                temp.setTitle(resultSet.getString("TITLE"));
                temp.setId(resultSet.getInt("ID"));
                result.add(temp);
            }
        } catch (SQLException e) {
            throw new DaoException("Trouble in BookDAO.readAll()", e);
        }
        return result;

    }



    @Override
    protected String getUpdateQuery() {
        return null;
    }

    @Override
    protected String getCreateQuery() {
        return null;
    }

    @Override
    protected String getDeleteQuery() {
        return null;
    }

    @Override
    protected String getReadQuery() {
        return "SELECT * FROM BOOK WHERE  ID LIKE ?";
    }

    @Override
    protected AbstractEntity getEntity(PreparedStatement statement) {
        return null;
    }

    @Override
    protected PreparedStatement setFieldsInDeleteStatement(PreparedStatement preparedStatement, AbstractEntity entity) {
        return null;
    }

    @Override
    protected PreparedStatement setFieldsInReadStatement(PreparedStatement preparedStatement, int id) {
        try {
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            throw new DaoException("Trouble with creating Read Statement in BookDao", e);
        }
        return preparedStatement;
    }

    @Override
    protected PreparedStatement setFieldsInCreateStatement(PreparedStatement statement, AbstractEntity entity) {
        return null;
    }

    @Override
    protected PreparedStatement setFieldsInCreateStatement(PreparedStatement statement, int id) {
        return null;
    }

    @Override
    protected PreparedStatement setFieldsInUpdateStatement(PreparedStatement preparedStatement, AbstractEntity entity) {
        return null;
    }


//    public static final String SELECT_ALL_BOOKS = "SELECT * FROM BOOK";
//    public static final String SELECT_BOOK_BY_ID = "SELECT * FROM BOOK WHERE  ID LIKE ?";
//    public static final String MY_LIBRARY_URL = "jdbc:h2:~/temp/library/myLibrary";
//
//    public List<Book> readAll() {
//        List<Book> result = new ArrayList<>();
//        Connection connection = null;
//        try {
//            connection = ConnectionPool.getInstance().getConnection();
//
//            PreparedStatement readAll = connection.prepareStatement(SELECT_ALL_BOOKS);
//            readAll.execute();
//            ResultSet resultSet = readAll.getResultSet();
//            while (resultSet.next()) {
//                Book temp = new Book();
//                temp.setAuthor(resultSet.getString("AUTHOR"));
//                temp.setTitle(resultSet.getString("TITLE"));
//                temp.setId(resultSet.getInt("ID"));
//                result.add(temp);
//            }
//        } catch (SQLException e) {
//            throw new DaoException("Trouble in BookDAO.readAll()", e);
//        } finally {
//            ConnectionPool.getInstance().returnConnection(connection);
//        }
//        return result;
//    }
//
//    public Book read(int id) {
//        Book result = new Book();
//        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa")) {
//            PreparedStatement read = connection.prepareStatement(SELECT_BOOK_BY_ID);
//            read.setInt(1, id);
//            read.execute();
//            ResultSet resultSet = read.getResultSet();
//            // TODO ADD VALIDATION! resultSet have to contain only 1 result
//            while (resultSet.next()) {
//                result.setId(resultSet.getInt("ID"));
//                result.setTitle(resultSet.getString("TITLE"));
//                result.setAuthor("AUTHOR");
//            }
//        } catch (SQLException e) {
//            throw new DaoException("Trouble in BookDAO.read()", e);
//        }
//        return result;
//    }
}
