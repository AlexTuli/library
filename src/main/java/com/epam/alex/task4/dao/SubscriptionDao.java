package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.Book;
import com.epam.alex.task4.entity.Subscription;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexTuli on 11/23/15.
 *
 * @author Bocharnikov Alexandr
 */
public class SubscriptionDao extends AbstractDao<Subscription> {

    private static final Logger log = Logger.getLogger(SubscriptionDao.class);

    public SubscriptionDao(Connection connection, DaoFactory factory) {
        super(connection, factory);
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO SUBSCRIPTION (ID, USER_ID) " +
                "VALUES (DEFAULT , ?)";
    }

    @Override
    protected PreparedStatement setFieldsInCreateStatement(PreparedStatement statement, Subscription subscription) {
        try {
            statement.setInt(1, subscription.getUser().getId());
        } catch (SQLException e) {
            throw new DaoException("Trouble in SubscriptionDao by setFieldsInCreateStatement()", e);
        }
        return statement;
    }

    @Override
    protected String getReadQuery() {
        return "SELECT SUBSCRIPTION.ID, BOOK.TITLE, BOOK.AUTHOR, BOOK_ID FROM SUBSCRIPTION\n" +
                "INNER JOIN SUBSCRIPTION_BOOK ON SUBSCRIPTION.ID = SUBSCRIPTION_BOOK.SUBSCRIPTION_ID\n" +
                "INNER JOIN BOOK ON SUBSCRIPTION_BOOK.BOOK_ID = BOOK.ID\n" +
                "WHERE SUBSCRIPTION.USER_ID = ?";
    }

    @Override
    protected PreparedStatement setFieldsInReadStatement(PreparedStatement statement, int id) {
        try {
            statement.setInt(1, id);
        } catch (SQLException e) {
            throw new DaoException("Trouble in creating Read statement Subscription", e);
        }
        return statement;
    }

    @Override
    protected String getReadByEntityQuery() {
        return "SELECT SUBSCRIPTION.ID FROM SUBSCRIPTION WHERE USER_ID = ?";
    }

    @Override
    protected PreparedStatement setFieldsInReadByEntityStatement(PreparedStatement statement, Subscription subscription) {
        try {
            statement.setInt(1, subscription.getUser().getId());
        } catch (SQLException e) {
            log.error("Trouble in creating Read by entity statement, SubscriptionDAO");
            throw new DaoException(e);
        }
        return statement;
    }

    @Override
    protected String getReadAllQuery() {
        return "SELECT BOOK.ID, USER_ID, SUBSCRIPTION_ID FROM SUBSCRIPTION\n" +
                "INNER JOIN SUBSCRIPTION_BOOK ON SUBSCRIPTION.ID = SUBSCRIPTION_BOOK.SUBSCRIPTION_ID\n" +
                "INNER JOIN BOOK ON SUBSCRIPTION_BOOK.BOOK_ID = BOOK.ID\n" +
                "ORDER BY SUBSCRIPTION_ID";
    }

    // TODO: 12/19/15 IF HAVE NO BOOKS, THIS RETURN null, but have to return ID
    @Override
    protected List<Subscription> parseResultSet(ResultSet resultSet) {
        List<Subscription> result = new ArrayList<>();
        try {
            int temp = -1; // Negative ID doesn't used in DB
            Subscription subscription = null;
            while (resultSet.next()) {
                int id = resultSet.getInt(4);
                log.debug("ID of current subscription is " + id);
                // If id of new subscription != id previous subscription, create new subscription, need to readAll()
                if (temp != id) {
                    temp = id;
                    if (subscription != null) {
                        result.add(subscription);
                    }
                    subscription = new Subscription();
                    log.debug("Set ID to subscription");
                    subscription.setId(id);
                }
                log.debug("Creating new book");
                Book book = new Book();
                try {
                    book.setTitle(resultSet.getString("AUTHOR"));
                } catch (SQLException e) {
                    book.setTitle(null);
                }
                try {
                    book.setAuthor(resultSet.getString("TITLE"));
                } catch (SQLException e) {
                    book.setAuthor(null);
                }
                book.setId(resultSet.getInt("ID"));
                log.debug("Book: " + book.getId() + " adding to subscription");
                if (subscription != null) {
                    subscription.addBook(book);
                }
            }
            log.debug("Add subscription to result");
            result.add(subscription);
        } catch (SQLException e) {
            log.error("Trouble in SubscriptionDao by parseResultSet()");
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected int parseGeneratedKeys(ResultSet generatedKeys) {

        return 0;
    }

    /**
     * To update subscription should get to DAO subscription with only 1 new book and id of subscription
     */
    @Override
    protected String getUpdateQuery() {
//        UPDATE SUBSCRIPTION_BOOK SET BOOK_ID = ? WHERE SUBSCRIPTION_ID LIKE ? AND BOOK_ID LIKE ?
        return "INSERT INTO SUBSCRIPTION_BOOK (BOOK_ID, SUBSCRIPTION_ID) VALUES (?, ?)";
    }

    /**
     * To update subscription should get to DAO subscription with only 1 new book and id of subscription
     */
    @Override
    protected PreparedStatement setFieldsInUpdateStatement(PreparedStatement statement, Subscription subscription) {
        try {
            statement.setInt(1, subscription.getBook(0).getId());
            statement.setInt(2, subscription.getId());
        } catch (SQLException e) {
            throw new DaoException("Trouble by setFieldInUpdateStatement in SubscriptiopnDao", e);
        }
        return statement;
    }

    /**
     * SQL query to delete one book from subscription
     *
     * @return
     */
    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM SUBSCRIPTION_BOOK " +
                "WHERE SUBSCRIPTION_ID LIKE ? AND BOOK_ID LIKE ?";
    }

    /**
     * Set to statement first book to delete
     */
    @Override
    protected PreparedStatement setFieldsInDeleteStatement(PreparedStatement statement, Subscription subscription) {
        try {
            statement.setInt(1, subscription.getId());
            statement.setInt(2, subscription.getBook(0).getId());
        } catch (SQLException e) {
            throw new DaoException("Trouble in SubscriptionDAO by setFieldsInDeleteStatement", e);
        }
        return statement;
    }

}

//    public static final String MY_LIBRARY_URL = "jdbc:h2:~/temp/library/myLibrary";
//
//    public static final String READ_SUBSCRIPTION = "SELECT BOOK.TITLE, BOOK.AUTHOR, BOOK_ID FROM\n" +
//            "(SELECT * FROM\n" +
//            "(SELECT SUBSCRIPTION.ID\n" +
//            "FROM USER\n" +
//            "INNER JOIN SUBSCRIPTION ON USER_ID = USER.ID\n" +
//            "WHERE USER_ID LIKE ?)\n" +
//            "INNER JOIN SUBSCRIPTION_BOOK WHERE ID LIKE SUBSCRIPTION_BOOK.SUBSCRIPTION_ID)\n" +
//            "INNER JOIN BOOK ON BOOK_ID = BOOK.ID;";
//
//    public static final String CREATE_SUBSCRIPTION = "INSERT INTO SUBSCRIPTION (ID, USER_ID) " +
//            "VALUES (DEFAULT , ?)";
//
//    public static final String DELETE_BOOK = "DELETE FROM SUBSCRIPTION_BOOK " +
//            "WHERE SUBSCRIPTION_ID LIKE ? AND BOOK_ID LIKE ?";
//
//
//    static {
//        try {
//            Class.forName("org.h2.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new DaoException("Trouble in SubscriptionDAO", e);
//        }
//    }
//
//    public Subscription read(User user) {
//
//        Subscription result = new Subscription();
//
//        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa")) {
//
//            PreparedStatement preparedStatement = connection.prepareStatement(READ_SUBSCRIPTION);
//            preparedStatement.setInt(1, user.getId());
//
//            preparedStatement.execute();
//
//            ResultSet resultSet = preparedStatement.getResultSet();
//
//            while (resultSet.next()) {
//                BookDao bookDao = new BookDao();
//                result.addBook(bookDao.read(resultSet.getInt("BOOK_ID")));
//            }
//
//        } catch (SQLException e) {
//            throw new DaoException("Trouble in SubscriptionDAO", e);
//        }
//        return result;
//    }
//
//    public void insert(Subscription subscription) {
//
//
//    }
//
//    *
//     * Update subscription in DB
//     *
//     * @param subscription - subscribe of User
//     * @return - subscribe of User
//
//    public Subscription update(Subscription subscription) {
//
//
//        // TODO FINISH THAT
//        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa")) {
//            Subscription current = read(subscription.getUser());
//            if (!current.equals(subscription)) {
//                PreparedStatement updateSubscription = connection.prepareStatement("");
//
//                BOOK_ID
//                IF (!contains) add to subsc
//
//
//            }
//        } catch (SQLException e) {
//            throw new DaoException("Trouble in SubscriptionDAO", e);
//        }
//
//        return subscription;
//
//    }
//
//    *
//     * Create empty subscription
//     *
//     * @param subscription - subscription of User
//
//    public void create(Subscription subscription) {
//
//        try (Connection connection = DriverManager.getConnection(MY_LIBRARY_URL, "sa", "sa")) {
//
//            PreparedStatement createSubscription = connection.prepareStatement(CREATE_SUBSCRIPTION);
//            createSubscription.setInt(1, subscription.getUser().getId());
//            createSubscription.execute();
//
//        } catch (SQLException e) {
//            throw new DaoException("Error in create SubscriptionDAO", e);
//        }
//    }

