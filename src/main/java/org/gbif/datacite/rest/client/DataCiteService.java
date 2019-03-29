package org.gbif.datacite.rest.client;

import org.gbif.datacite.model.json.Datacite42Schema;

/**
 * GBIF DataCite Service client.
 */
public interface DataCiteService {

    /**
     * Gets a list of dois.
     *
     * @return a list of dois wrapped by {@link Datacite42Schema}
     */
    Datacite42Schema getDois();

    /**
     * Get a doi.
     *
     * @param doi identifier
     * @return a doi  wrapped by {@link Datacite42Schema}
     */
    Datacite42Schema getDoi(String doi);

    /**
     * Create a doi.
     *
     * @param body data
     */
    void createDoi(Datacite42Schema body);

    /**
     * Update a doi.
     *
     * @param doi identifier
     * @param body data
     */
    void updateDoi(String doi, Datacite42Schema body);

    /**
     * Delete a doi.
     *
     * @param doi identifier
     */
    void deleteDoi(String doi);
}
