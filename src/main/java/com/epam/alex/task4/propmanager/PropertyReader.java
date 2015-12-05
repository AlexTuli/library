package com.epam.alex.task4.propmanager;

import java.util.Map;

/**
 * Describe the different ways to read property
 * Created by AlexTuli on 12/5/15.
 *
 * @author Bocharnikov Alexandr
 */
public interface PropertyReader {

    Map<String, String> readProperty(String key);
}
