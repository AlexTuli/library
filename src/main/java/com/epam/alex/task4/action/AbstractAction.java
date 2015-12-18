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
    protected DaoFactory factory;

    public AbstractAction(DaoFactory factory) {
        this.factory = factory;
    }

    public abstract String execute(HttpServletRequest request, HttpServletResponse response);

    protected void commit() {
        try {
            log.debug("Start to commit");
            factory.commit();
            log.debug("Stop transaction");
            factory.stopTransaction();
        } catch (SQLException e) {
            log.error("Can't commit", e);
            throw new ActionException("Can't commit", e);
        }
    }

    protected void startTransaction() {
        try {
            log.debug("Start transaction");
            factory.startTransaction();
        } catch (SQLException e) {
            log.error("Failed to start transaction", e);
            throw new ActionException("Can't start transaction", e);
        }
    }

    protected void rollback() {
        try {
            log.debug("Rollback");
            factory.rollback();
            factory.stopTransaction();
        } catch (SQLException e1) {
            log.error("Can't rollback", e1);
            throw new ActionException("Can't rollback", e1);
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
