package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.Notification;

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
public class NotificationDao extends AbstractDao<Notification> {

    public NotificationDao(Connection connection, DaoFactory factory) {
        super(connection, factory);
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO NOTIFICATION (ID, USER_ID, TEXT) VALUES (DEFAULT, ?, ?)";
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
        return "SELECT TEXT FROM NOTIFICATION WHERE USER_ID LIKE ?";
    }

    @Override
    protected String getReadByEntityQuery() {
        return null;
    }

    /**
     * @param statement
     * @param id USER_ID NOT NOTIFICATION ID
     * @return
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
        return "SELECT TEXT, USER_ID FROM NOTIFICATION";
    }

    @Override
    protected PreparedStatement setFieldsInReadByEntityStatement(PreparedStatement preparedStatement, Notification notification) {
        return null;
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

    @Override
    protected int parseGeneratedKeys(ResultSet generatedKeys) {

        return 0;
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE NOTIFICATION SET TEXT = ? WHERE ID LIKE ?";
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
        return "DELETE FROM NOTIFICATION WHERE ID LIKE ?";
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


//    public static final String MY_LIBRARY_URL = "jdbc:h2:~/temp/library/myLibrary";
//    public static final String SELECT_NOTIFICATION_BY_USER = "SELECT * FROM NOTIFICATION " +
//            "WHERE USER_ID LIKE ?";
//
//    static {
//        try {
//            Class.forName("org.h2.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new DaoException("Trouble in SubscriptionDAO", e);
//        }
//    }
//
//    public List<Notification> get(User user) {
////TODO FIX!
//        List<Notification> result = new ArrayList<>();
//
//        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa")) {
//            PreparedStatement getNotification = connection.prepareStatement(SELECT_NOTIFICATION_BY_USER);
//            getNotification.setInt(1, user.getId());
//            getNotification.execute();
//            ResultSet resultSet = getNotification.getResultSet();
//            while (resultSet.next()) {
//                Notification temp = new Notification();
//                temp.setUser(user);
//                temp.setID(resultSet.getInt(1));
//                String string = resultSet.getString(3);
//                temp.setText(string);
//            }
//
//        } catch (SQLException e) {
//            throw new DaoException("Trouble with get notification", e);
//        }
//        return result;
//    }
//
//    public boolean remove(Notification notification) {
//        return false;
//    }
}
