
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author rahul.kumar
 * @version $Id: PropertyUtil.java, v 0.1 2020-02-01 12:24 rahul.kumar Exp $$
 */
public class PropertyUtil {

    private static PropertyUtil prop;
    private Properties properties;

    private PropertyUtil() {
        properties = new Properties();
    }

    public static synchronized PropertyUtil getInstance() {
        if (prop == null) {
            prop = new PropertyUtil();
        }
        return prop;
    }


    public void load(String fileName) {
        InputStream input;
        input = getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load(File file) {
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Clears this Properties object so that it contains no keys.
     */
    public void clear() {
        properties.clear();
    }

    /**
     * Reads from the property file and provides the values of specified key.
     *
     * @param key The key whose value is required to be read.
     * @return The value of specified key.
     */
    public String getValue(String key) {
        return properties.getProperty(key).trim();
    }

    /**
     * Reads from the property file and provides the values of specified key, but if that key is not present then it will return the default value.
     *
     * @param key          The key whose value is required to be read.
     * @param defaultValue a defaultValue of the specified key if that key is not present or
     * @return The value of specified key or the default value.
     */
    public String getValue(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue).trim();
    }

    /**
     * Sets the value of specified key in the property file.
     *
     * @param key   The key whose values is required to be set.
     * @param value The value that needed to be set for the specified key.
     */
    public void setValue(String key, String value) {
        properties.setProperty(key, value);
    }
}