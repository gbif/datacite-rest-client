package org.gbif.datacite.rest.client;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import org.gbif.datacite.model.json.Datacite42Schema;
import org.gbif.datacite.rest.client.model.DoiSimplifiedModel;
import retrofit2.Response;

/**
 * GBIF DataCite Service client.
 * See {@link Datacite42Schema}
 */
public interface DataCiteClient {

    /**
     * Get a doi.
     *
     * @param doi identifier
     * @return doi in JSON API format wrapped by {@link Response}
     */
    Response<JSONAPIDocument<Datacite42Schema>> getDoi(String doi);

    /**
     * Create a doi.
     *
     * @param body data
     * @return created object in JSON API format wrapped by {@link Response}
     */
    Response<JSONAPIDocument<Datacite42Schema>> createDoi(JSONAPIDocument<DoiSimplifiedModel> body);

    /**
     * Update a doi.
     *
     * @param doi identifier
     * @param body data
     * @return updated object in JSON API format wrapped by {@link Response}
     */
    Response<JSONAPIDocument<Datacite42Schema>> updateDoi(String doi, JSONAPIDocument<DoiSimplifiedModel> body);

    /**
     * Delete a doi.
     *
     * @param doi identifier
     * @return {@link Response} with empty body
     */
    Response<Void> deleteDoi(String doi);

    /**
     * Get doi XML metadata as String.
     *
     * @param doi identifier
     * @return doi XML metadata wrapped by {@link Response}
     */
    Response<String> getMetadata(String doi);
}
