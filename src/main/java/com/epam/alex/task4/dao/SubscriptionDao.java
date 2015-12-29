package com.epam.alex.task4.dao;

import com.epam.alex.task4.entity.Book;
import com.epam.alex.task4.entity.Subscription;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao to work with subscription in DB.
 * Created by AlexTuli on 11/23/15.
 *
 * @author Bocharnikov Alexandr
 */
public class SubscriptionDao extends AbstractDao<Subscription> {

    private static final Logger log = Logger.getLogger(SubscriptionDao.class);
    public static final String CREATE_QUERY = "INSERT INTO SUBSCRIPTION (ID, USER_ID) " +
            "VALUES (DEFAULT , ?)";
    public static final String READ_BY_ID_QUERY = "SELECT SUBSCRIPTION.ID, BOOK.TITLE, BOOK.AUTHOR, BOOK_ID FROM SUBSCRIPTION\n" +
            "INNER JOIN SUBSCRIPTION_BOOK ON SUBSCRIPTION.ID = SUBSCRIPTION_BOOK.SUBSCRIPTION_ID\n" +
            "INNER JOIN BOOK ON SUBSCRIPTION_BOOK.BOOK_ID = BOOK.ID\n" +
            "WHERE SUBSCRIPTION.USER_ID = ?";
    public static final String ID_READ_QUERY = "SELECT SUBSCRIPTION.ID FROM SUBSCRIPTION WHERE USER_ID = ?";
    public static final String READ_ALL_QUERY = "SELECT BOOK.ID, USER_ID, SUBSCRIPTION_ID FROM SUBSCRIPTION\n" +
            "INNER JOIN SUBSCRIPTION_BOOK ON SUBSCRIPTION.ID = SUBSCRIPTION_BOOK.SUBSCRIPTION_ID\n" +
            "INNER JOIN BOOK ON SUBSCRIPTION_BOOK.BOOK_ID = BOOK.ID\n" +
            "ORDER BY SUBSCRIPTION_ID";
    public static final String UPDATE_QUERY = "INSERT INTO SUBSCRIPTION_BOOK (BOOK_ID, SUBSCRIPTION_ID) VALUES (?, ?)";
    public static final String DELETE_QUERY = "DELETE FROM SUBSCRIPTION_BOOK " +
            "WHERE SUBSCRIPTION_ID LIKE ? AND BOOK_ID = ?";

    public SubscriptionDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getCreateQuery() {
        return CREATE_QUERY;
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
        return READ_BY_ID_QUERY;
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
        return ID_READ_QUERY;
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
        return READ_ALL_QUERY;
    }

    @Override
    protected List<Subscription> parseResultSet(ResultSet resultSet) {
        List<Subscription> result = new ArrayList<>();
        try {
            int temp = -1; // Negative ID doesn't used in DB
            Subscription subscription = null;
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            log.debug("Column count is " + columnCount);
            while (resultSet.next()) {
                if (columnCount == 1) {
                    log.debug("Get ID from rs");
                    int id = resultSet.getInt(1);
                    subscription = new Subscription();
                    subscription.setId(id);
                } else {
                    int subscriptionId = resultSet.getInt(1);
                    log.debug("ID of current subscription is " + subscriptionId);
                    if (temp != subscriptionId) { // If id of new subscription != id previous subscription, create new subscription, need to readAll()
                        temp = subscriptionId;
                        if (subscription != null) {
                            result.add(subscription);
                        }
                        subscription = new Subscription();
                        log.debug("Set ID to subscription");
                        subscription.setId(subscriptionId);
                    }
                    log.debug("Creating new book");
                    Book book = new Book();
                    try {
                        book.setTitle(resultSet.getString("AUTHOR"));
                    } catch (SQLException e) {
                        log.warn("Author is null");
                        book.setTitle(null);
                    }
                    try {
                        book.setAuthor(resultSet.getString("TITLE"));
                    } catch (SQLException e) {
                        log.warn("Title is null");
                        book.setAuthor(null);
                    }
                    book.setId(resultSet.getInt(4));
                    log.debug("Book: " + book.getId() + " adding to subscription");
                    if (subscription != null) {
                        subscription.addBook(book);
                    }
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

    /**
     * Does not required yet
     */
    @Override
    protected int parseGeneratedKeys(ResultSet generatedKeys) {
        return 0;
    }

    /**
     * To update subscription should get to DAO subscription with only 1 new book and id of subscription
     */
    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
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
     */
    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
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