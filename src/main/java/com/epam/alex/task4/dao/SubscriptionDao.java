package com.epam.alex.task4.dao;

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
    public static final String READ_SUBSCRIPTION = "SELECT * FROM\n" +
            "(SELECT * FROM\n" +
            "(SELECT SUBSCRIPTION.ID\n" +
            "FROM USER\n" +
            "INNER JOIN SUBSCRIPTION ON USER_ID = USER.ID\n" +
            "WHERE USER_ID LIKE ?)\n" +
            "INNER JOIN SUBSCRIPTION_BOOK WHERE ID LIKE SUBSCRIPTION_BOOK.SUBSCRIPTION_ID)\n" +
            "INNER JOIN BOOK ON BOOK_ID = BOOK.ID;\n";

    public Subscription read(User user) {

        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL)) {

            Class.forName("org.h2.drivers");

            PreparedStatement preparedStatement = connection.prepareStatement(READ_SUBSCRIPTION);
            preparedStatement.setInt(1, user.getId());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            System.out.println(resultSet);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
