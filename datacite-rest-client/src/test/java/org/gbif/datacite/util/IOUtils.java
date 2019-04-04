package org.gbif.datacite.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Provides utility methods for IO related actions.
 */
public class IOUtils {

    /**
     * For getting file content by name as a string.
     *
     * @param name file name
     * @return character data (String)
     * @throws IOException in case of some input/output problems
     */
    public static String getResourceAsString(String name) throws IOException {
        InputStream input = IOUtils.class.getClassLoader().getResourceAsStream(name);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            StringBuilder resultBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                resultBuilder.append(line);
            }

            return resultBuilder.toString();
        }
    }

    /**
     * For getting file content by name as byte data.
     *
     * @param name file name
     * @return byte data (InputStream)
     */
    public static InputStream getResource(String name) {
        return IOUtils.class.getClassLoader().getResourceAsStream(name);
    }
}
