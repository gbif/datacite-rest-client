package org.gbif.datacite.rest.client.retrofit;

import org.gbif.datacite.model.json.Datacite42Schema;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * GBIF DataCite Retrofit Service client.
 * Provides CRUD operations.
 */
public interface DataCiteRetrofitService {

    /**
     * Return a list of dois.
     *
     * @return a list of dois
     */
    @Headers({"Accept: application/vnd.api+json"})
    @GET("/dois")
    Call<Datacite42Schema> get();

    // TODO: 2019-03-29 authorization required!
    /**
     * Represents a DOI and provides access to metadata attributes.
     *
     * @param body data
     * @return HTTP 201
     */
    @Headers({"Accept: application/vnd.api+json"})
    @POST("/dois")
    Call<Void> create(@Body Datacite42Schema body);

    /**
     * Returns a doi.
     *
     * @param doi identifier
     * @return a doi
     */
    @Headers({"Accept: application/vnd.api+json"})
    @GET("/dois/{id}")
    Call<Datacite42Schema> get(@Path("id") String doi);

    // TODO: 2019-03-29 authorization required!
    /**
     * Update a doi.
     *
     * @param doi identifier
     * @param body data
     * @return HTTP 200
     */
    @Headers({"Accept: application/vnd.api+json"})
    @PUT("/dois/{id}")
    Call<Void> update(@Path("id") String doi, @Body Datacite42Schema body);

    // TODO: 2019-03-29 authorization required!
    /**
     * Delete a doi.
     *
     * @param doi identifier
     * @return HTTP 204
     */
    @Headers({"Accept: application/vnd.api+json"})
    @GET("/dois/{id}")
    Call<Void> delete(@Path("id") String doi);

}
