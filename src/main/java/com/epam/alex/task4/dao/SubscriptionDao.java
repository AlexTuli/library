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
        return "SELECT BOOK.TITLE, BOOK.AUTHOR, BOOK_ID FROM\n" +
                "(SELECT * FROM\n" +
                "(SELECT SUBSCRIPTION.ID\n" +
                "FROM USER\n" +
                "INNER JOIN SUBSCRIPTION ON USER_ID = USER.ID\n" +
                "WHERE USER_ID LIKE ?)\n" +
                "INNER JOIN SUBSCRIPTION_BOOK WHERE ID LIKE SUBSCRIPTION_BOOK.SUBSCRIPTION_ID)\n" +
                "INNER JOIN BOOK ON BOOK_ID = BOOK.ID";
    }

    @Override
    protected String getReadByEntityQuery() {
        return null;
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
    protected String getReadAllQuery() {
        return "SELECT BOOK.ID, USER_ID, SUBSCRIPTION_ID FROM SUBSCRIPTION\n" +
                "INNER JOIN SUBSCRIPTION_BOOK ON SUBSCRIPTION.ID = SUBSCRIPTION_BOOK.SUBSCRIPTION_ID\n" +
                "INNER JOIN BOOK ON SUBSCRIPTION_BOOK.BOOK_ID = BOOK.ID\n" +
                "ORDER BY SUBSCRIPTION_ID";
    }

    @Override
    protected PreparedStatement setFieldsInReadByEntityStatement(PreparedStatement preparedStatement, Subscription subscription) {
        return null;
    }

    @Override
    protected List<Subscription> parseResultSet(ResultSet resultSet) {
        List<Subscription> result = new ArrayList<>();
        try {
            Subscription subscription = new Subscription();
            while (resultSet.next()) {
//                BOOK.TITLE, BOOK.AUTHOR, BOOK_ID
                // TODO: 12/13/15 ADD SUBSCRIPTION_ID and IF IT THE SAME AS PREVIOUS ADD BOOK TO OLD SUBSCRIPTION ELSE TO NEW
                log.debug("Creating new book");
                Book book = new Book();
                book.setTitle(resultSet.getString(1));
                book.setAuthor(resultSet.getString(2));
                book.setId(resultSet.getInt(3));
                log.debug("Book: " + book.getId() + " adding to subscription");
                subscription.addBook(book);
            }
            result.add(subscription);
        } catch (SQLException e) {
            throw new DaoException("Trouble in SubscriptionDao by parseResultSet()", e);
        }
        return result;
    }

    @Override
    protected int parseGeneratedKeys(ResultSet generatedKeys) {

        return 0;
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE SUBSCRIPTION_BOOK SET BOOK_ID = ? WHERE SUBSCRIPTION_ID LIKE ? AND BOOK_ID LIKE ?";
    }

    @Override
    protected PreparedStatement setFieldsInUpdateStatement(PreparedStatement statement, Subscription subscription) {
        // TODO Figure out this
        //statement.setInt(1);
        return null;
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
     * Set to statement first book to delete     *
     *
     * @param statement
     * @param subscription
     * @return
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

