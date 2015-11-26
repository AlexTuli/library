package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.Book;
import com.epam.alex.task4.entity.Subscription;
import com.epam.alex.task4.entity.User;

import java.sql.*;

/**
 * Created by AlexTuli on 11/23/15.
 *
 * @author Bocharnikov Alexandr
 */
public class SubscriptionDao {

    // TODO ADD DRIVER AND FIX read() METHOD

    public static final String MY_LIBRARY_URL = "jdbc:h2:~/temp/library/myLibrary";

    public static final String READ_SUBSCRIPTION = "SELECT BOOK.TITLE, BOOK.AUTHOR, BOOK_ID FROM\n" +
            "(SELECT * FROM\n" +
            "(SELECT SUBSCRIPTION.ID\n" +
            "FROM USER\n" +
            "INNER JOIN SUBSCRIPTION ON USER_ID = USER.ID\n" +
            "WHERE USER_ID LIKE ?)\n" +
            "INNER JOIN SUBSCRIPTION_BOOK WHERE ID LIKE SUBSCRIPTION_BOOK.SUBSCRIPTION_ID)\n" +
            "INNER JOIN BOOK ON BOOK_ID = BOOK.ID;";

    public Subscription read(User user) {

        Subscription result = new Subscription();

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new SubscriptionDaoException("Trouble in SubscriptionDAO", e);
        }

        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa")) {



            PreparedStatement preparedStatement = connection.prepareStatement(READ_SUBSCRIPTION);
            preparedStatement.setInt(1, user.getId());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Book book = new Book();
                book.setAuthor(resultSet.getString("AUTHOR"));
                book.setTitle(resultSet.getString("TITLE"));
                book.setId(resultSet.getString("BOOK_ID"));
                result.addBook(book);
                result.setUser(user);
            }


        } catch (SQLException e) {
            throw new SubscriptionDaoException("Trouble in SubscriptionDAO", e);
        }
        return result;
    }
}
