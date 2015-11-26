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
public class BookDao {

    public static final String SELECT_ALL_BOOKS = "SELECT * FROM BOOK";
    public static final String SELECT_BOOK_BY_ID = "SELECT * FROM BOOK WHERE  ID LIKE ?";

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new DaoException("Trouble in BookDAO by getting driver to H2", e);
        }
    }

    public static final String MY_LIBRARY_URL = "jdbc:h2:~/temp/library/myLibrary";

    public List<Book> readAll() {
        List<Book> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa")) {
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
            throw new DaoException("Trouble in BookDAO.readAll()",e);
        }
        return result;
    }

    public Book read(int id) {
        Book result = new Book();
        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa")) {
            PreparedStatement read = connection.prepareStatement(SELECT_BOOK_BY_ID);
            read.setInt(1, id);
            read.execute();
            ResultSet resultSet = read.getResultSet();
            // TODO ADD VALIDATION! resultSet have to contain only 1 result
            while (resultSet.next()) {
                result.setId(resultSet.getInt("ID"));
                result.setTitle(resultSet.getString("TITLE"));
                result.setAuthor("AUTHOR");
            }
        } catch (SQLException e) {
            throw new DaoException("Trouble in BookDAO.read()",e);
        }
        return result;
    }
}
