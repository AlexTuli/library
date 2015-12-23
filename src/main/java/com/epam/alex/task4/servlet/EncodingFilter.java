package com.epam.alex.task4.servlet;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by AlexTuli on 12/15/15.
 *
 * @author Bocharnikov Alexandr
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = "/controller")
public class EncodingFilter implements Filter {

    private static final Logger log = Logger.getLogger(EncodingFilter.class);
    public static final String ENCODING = "UTF-8";
//    ISO-8859-1

    public void destroy() {
        log.info("EncodingFilter destroyed");
    }

    /**
     * Change encoding of request to UTF-8
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        log.info("doFilter()");
        req.setCharacterEncoding(ENCODING);
        resp.setCharacterEncoding(ENCODING);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        log.info("EncodingFilter started");
    }

}
