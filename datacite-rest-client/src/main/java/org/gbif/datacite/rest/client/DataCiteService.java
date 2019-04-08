package org.gbif.datacite.rest.client;

import com.github.jasminb.jsonapi.JSONAPIDocument;
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
    JSONAPIDocument<Datacite42Schema> getDois();

    /**
     * Get a doi.
     *
     * @param doi identifier
     * @return a doi  wrapped by {@link Datacite42Schema}
     */
    JSONAPIDocument<Datacite42Schema> getDoi(String doi);

    /**
     * Create a doi.
     *
     * @param body data
     * @return created object in JSON API format
     */
    JSONAPIDocument<Datacite42Schema> createDoi(JSONAPIDocument<Datacite42Schema> body);

    /**
     * Update a doi.
     *
     * @param doi identifier
     * @param body data
     * @return updated object in JSON API format
     */
    JSONAPIDocument<Datacite42Schema> updateDoi(String doi, JSONAPIDocument<Datacite42Schema> body);

    /**
     * Delete a doi.
     *
     * @param doi identifier
     */
    void deleteDoi(String doi);
}
