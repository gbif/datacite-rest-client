package org.gbif.datacite.rest.client.retrofit;

import java.util.ResourceBundle;

/**
 * Class for getting access to the IT's properties files.
 */
public class ITPropertiesManager {

    private static final String APPLICATION_PROPERTY_FILE = "it";
    private ResourceBundle bundle;
    private static ITPropertiesManager instance;

    private ITPropertiesManager() {
        bundle = ResourceBundle.getBundle(APPLICATION_PROPERTY_FILE);
    }

    /**
     * Singleton ITPropertiesManager.
     *
     * @return instance of ITPropertiesManager
     */
    public static ITPropertiesManager getInstance() {
        if (instance == null) {
            instance = new ITPropertiesManager();
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
