package com.epam.alex.task4.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Actions used to do something work. Implements controller part of MVC
 * Created by AlexTuli on 12/1/15.
 *
 * @author Bocharnikov Alexandr
 */
public interface Action {

    String execute(HttpServletRequest request, HttpServletResponse response);

}
