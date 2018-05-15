package com.epam.alex.library.propmanager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * This Property reader read all properties
 * Created by AlexTuli on 12/5/15.
 *
 * @author Bocharnikov Alexandr
 */
public class FullPropertyReader implements PropertyReader {

    /**
     * Read property by fileName
     *
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
            input = FullPropertyReader.class.getClassLoader().getResourceAsStream(fileName);
            prop.load(input);
            Set<String> strings = prop.stringPropertyNames();
            for (String string : strings) {
                result.put(string, prop.getProperty(string));
            }
        } catch (IOException e) {
            throw new PropertyReaderException("Trouble in FullPropertyReader", e);
        }
        return result;
    }
}
