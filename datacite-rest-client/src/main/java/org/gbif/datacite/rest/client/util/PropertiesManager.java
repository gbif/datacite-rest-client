package org.gbif.datacite.rest.client.util;

import java.util.ResourceBundle;

/**
 * Class for getting access to the application properties files.
 */
// TODO: 03/10/2019 Consider using PropertiesUtils
public class PropertiesManager {

    private static final String APPLICATION_PROPERTY_FILE = "app";
    private ResourceBundle bundle;
    private static PropertiesManager instance;

    private PropertiesManager() {
        bundle = ResourceBundle.getBundle(APPLICATION_PROPERTY_FILE);
    }

    /**
     * Singleton PropertiesManager.
     *
     * @return instance of PropertiesManager
     */
    public static PropertiesManager getInstance() {
        if (instance == null) {
            instance = new PropertiesManager();
        }

        return instance;
    }

    /**
     * Gets property value by key.
     *
     * @param key property key
     * @return property value
     */
    public String get(String key) {
        return bundle.getString(key);
    }
}
