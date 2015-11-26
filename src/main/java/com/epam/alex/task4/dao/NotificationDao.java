package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.Notification;
import com.epam.alex.task4.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexTuli on 11/26/15.
 *
 * @author Bocharnikov Alexandr
 */
public class NotificationDao {

    public static final String MY_LIBRARY_URL = "jdbc:h2:~/temp/library/myLibrary";
    public static final String SELECT_NOTIFICATION_BY_USER = "SELECT * FROM NOTIFICATION " +
            "WHERE USER_ID LIKE ?";

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new DaoException("Trouble in SubscriptionDAO", e);
        }
    }

    public List<Notification> get(User user) {
//TODO FIX!
        List<Notification> result = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa")) {
            PreparedStatement getNotification = connection.prepareStatement(SELECT_NOTIFICATION_BY_USER);
            getNotification.setInt(1, user.getId());
            getNotification.execute();
            ResultSet resultSet = getNotification.getResultSet();
            while (resultSet.next()) {
                Notification temp = new Notification();
                temp.setUser(user);
                temp.setID(resultSet.getInt(1));
                String string = resultSet.getString(3);
                temp.setText(string);
            }

        } catch (SQLException e) {
            throw new DaoException("Trouble with get notification", e);
        }
        return result;
    }

    public boolean remove(Notification notification) {
        return false;
    }
}
