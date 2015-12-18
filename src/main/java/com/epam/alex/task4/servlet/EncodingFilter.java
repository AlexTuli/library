package com.epam.alex.task4.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by AlexTuli on 12/15/15.
 *
 * @author Bocharnikov Alexandr
 */
@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {

    public static final String ENCODING = "ISO-8859-1";

    public void destroy() {
    }

    // TODO: 12/18/15 Fix it
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);
        resp.setCharacterEncoding(ENCODING);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
