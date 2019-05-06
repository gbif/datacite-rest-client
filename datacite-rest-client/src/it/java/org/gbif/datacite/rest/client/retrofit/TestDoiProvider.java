package org.gbif.datacite.rest.client.retrofit;

import java.time.Instant;

/**
 * Provides a new unique DOI based on time.
 */
class TestDoiProvider {

    /**
     * Provides a new unique DOI based on time.
     *
     * @param prefix DOI prefix
     * @return new unique DOI
     */
    static String get(String prefix) {
        return prefix + "/" + Instant.now().toEpochMilli();
    }
}
