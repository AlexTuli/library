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

    public static final String MY_LIBRARY_URL = "jdbc:h2:~/temp/library/myLibrary";

    public static final String READ_SUBSCRIPTION = "SELECT BOOK.TITLE, BOOK.AUTHOR, BOOK_ID FROM\n" +
            "(SELECT * FROM\n" +
            "(SELECT SUBSCRIPTION.ID\n" +
            "FROM USER\n" +
            "INNER JOIN SUBSCRIPTION ON USER_ID = USER.ID\n" +
            "WHERE USER_ID LIKE ?)\n" +
            "INNER JOIN SUBSCRIPTION_BOOK WHERE ID LIKE SUBSCRIPTION_BOOK.SUBSCRIPTION_ID)\n" +
            "INNER JOIN BOOK ON BOOK_ID = BOOK.ID;";

    public static final String CREATE_SUBSCRIPTION = "INSERT INTO SUBSCRIPTION (ID, USER_ID) " +
            "VALUES (DEFAULT , ?)";

    public static final String DELETE_BOOK = "DELETE FROM SUBSCRIPTION_BOOK " +
            "WHERE SUBSCRIPTION_ID LIKE ? AND BOOK_ID LIKE ?";


    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new DaoException("Trouble in SubscriptionDAO", e);
        }
    }

    public Subscription read(User user) {

        Subscription result = new Subscription();

        // TODO change "sa" to username & password
        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa")) {

            PreparedStatement preparedStatement = connection.prepareStatement(READ_SUBSCRIPTION);
            preparedStatement.setInt(1, user.getId());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                BookDao bookDao = new BookDao();
                result.addBook(bookDao.read(resultSet.getInt("BOOK_ID")));
            }

        } catch (SQLException e) {
            throw new DaoException("Trouble in SubscriptionDAO", e);
        }
        return result;
    }

    public void insert(Subscription subscription) {


    }

    /**
     * Update subscription in DB
     *
     * @param subscription - subscribe of User
     * @return - subscribe of User
     */
    public Subscription update(Subscription subscription) {


        // TODO FINISH THAT
        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa")) {
            Subscription current = read(subscription.getUser());
            if (!current.equals(subscription)) {
                PreparedStatement updateSubscription = connection.prepareStatement("");
                /*
                BOOK_ID
                IF (!contains) add to subsc
                 */

            }
        } catch (SQLException e) {
            throw new DaoException("Trouble in SubscriptionDAO", e);
        }

        return subscription;

    }

    /**
     * Create empty subscription
     *
     * @param subscription - subscription of User
     */
    public void create(Subscription subscription) {

        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa")) {

            PreparedStatement createSubscription = connection.prepareStatement(CREATE_SUBSCRIPTION);
            createSubscription.setInt(1, subscription.getUser().getId());
            createSubscription.execute();

        } catch (SQLException e) {
            throw new DaoException("Error in create SubscriptionDAO", e);
        }
    }

    public boolean remove(Subscription subscription) {

        boolean b = false;

        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa")) {

            PreparedStatement deleteSubscription = connection.prepareStatement(DELETE_BOOK);
            deleteSubscription.setInt(1, subscription.getID());
            deleteSubscription.setInt(2, subscription.getBook(0).getId());
            deleteSubscription.execute();

            b = true;
        } catch (SQLException e) {
            throw new DaoException("Trouble with removing in SubscribeDAO", e);
        }

        return b;
    }


}
