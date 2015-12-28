package com.epam.alex.task4.action;

import com.epam.alex.task4.dao.DaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by AlexTuli on 12/11/15.
 *
 * @author Bocharnikov Alexandr
 */
public abstract class AbstractAction implements Action {

    private static final Logger log = Logger.getLogger(AbstractAction.class);
    protected DaoFactory daoFactory;

    public AbstractAction() {
        daoFactory = new DaoFactory();
    }

    /**
     * Implements a different ways to do something
     *
     * @return View - address to redirect or forward
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response);

    /**
     * Commit changes in DB and stop transaction. Also return connection to DB
     */
    protected void commit() {
        try {
            log.debug("Start to commit");
            daoFactory.commit();
            log.debug("Stop transaction");
            daoFactory.stopTransaction();
        } catch (SQLException e) {
            log.error("Can't commit", e);
            throw new ActionException("Can't commit", e);
        } finally {
            daoFactory.close();
        }
    }

    /**
     * Start transaction in DB
     */
    protected void startTransaction() {
        try {
            log.debug("Start transaction");
            daoFactory.startTransaction();
        } catch (SQLException e) {
            log.error("Failed to start transaction", e);
            throw new ActionException("Can't start transaction", e);
        }
    }

    /**
     * Rollback changes in DB and stop transaction. Also return connection to DB
     */
    protected void rollback() {
        try {
            log.debug("Rollback");
            daoFactory.rollback();
            daoFactory.stopTransaction();
        } catch (SQLException e1) {
            log.error("Can't rollback", e1);
            throw new ActionException("Can't rollback", e1);
        } finally {
            daoFactory.close();
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
