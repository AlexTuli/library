package com.epam.alex.task4.propmanager;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * This Property reader read propery by one
 * Created by AlexTuli on 12/5/15.
 *
 * @author Bocharnikov Alexandr
 */
public class KeyPropertyReader implements PropertyReader {

    /**
     * Name of property file
     */

    /**
     * Read property by fileName
     * @param fileName - fileName of property
     * @return Map of properties
     * @throws PropertyReaderException
     */
    @Override
    public Map<String, String> readProperty(String fileName) {
        Map<String, String> result = new HashMap<>();
        Properties prop = new Properties();
        InputStream input;
        try {
            input = KeyPropertyReader.class.getClassLoader().getResourceAsStream(fileName);
            prop.load(input);
            Set<String> strings = prop.stringPropertyNames();
            for (String string : strings) {
                result.put(string, prop.getProperty(string));
            }
        } catch (IOException e) {
            throw new PropertyReaderException("Trouble in KeyPropertyReader", e);
        }
        return result;
    }
}
