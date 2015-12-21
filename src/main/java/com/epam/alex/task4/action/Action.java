package com.epam.alex.task4.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by AlexTuli on 12/1/15.
 *
 * @author Bocharnikov Alexandr
 */
public interface Action {

    String execute(HttpServletRequest request, HttpServletResponse response);

}
