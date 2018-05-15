package com.epam.alex.library.propmanager;

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
