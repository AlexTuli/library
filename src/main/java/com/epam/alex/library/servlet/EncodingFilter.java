package com.epam.alex.library.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Change encoding to UTF-8
 * Created by AlexTuli on 12/15/15.
 *
 * @author Bocharnikov Alexandr
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = "/controller")
public class EncodingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(EncodingFilter.class);
    private static final String ENCODING = "UTF-8";

    public void destroy() {
        log.info("EncodingFilter destroyed");
    }

    /**
     * Change encoding to UTF-8
     */

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        log.info("doFilter()");
        req.setCharacterEncoding(ENCODING);
        resp.setCharacterEncoding(ENCODING);
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        log.info("EncodingFilter started");
    }

}
