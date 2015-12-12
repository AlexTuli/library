package com.epam.alex.task4.propmanager;

import java.util.Map;

/**
 * Read property file and then provide access to properties by key
 * Created by AlexTuli on 12/5/15.
 *
 * @author Bocharnikov Alexandr
 */
public class PropertyReaderFactory {

    /**
     * Map of properties. Use volatile to prevent multi-thread access
     */
    private volatile static Map<String, String> propertiesMap;
    PropertyReader propertyReader;
    String fileName;

    public PropertyReaderFactory(String fileName) {
        this.fileName = fileName;
    }

    public String getProperty(String key) {
        if (propertiesMap == null) {
            synchronized (PropertyReaderFactory.class) {
                if (propertiesMap == null) propertiesMap = propertyReader.readProperty(fileName);
            }
        }
        return propertiesMap.get(key);
    }

}
