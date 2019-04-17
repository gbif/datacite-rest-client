package org.gbif.datacite.rest.client.retrofit;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import org.gbif.datacite.model.json.Datacite42Schema;
import org.gbif.datacite.rest.client.model.DoiSimplifiedModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * GBIF DataCite Retrofit Service client.
 * Provides CRUD operations.
 */
public interface DataCiteRetrofitClient {

    /**
     * Return a list of dois.
     *
     * @return a list of dois
     */
    @Headers({"Accept: application/vnd.api+json"})
    @GET("/dois")
    Call<JSONAPIDocument<Datacite42Schema>> get();

    /**
     * Represents a DOI and provides access to metadata attributes.
     *
     * @param body data
     * @return HTTP 201
     */
    @Headers({"Accept: application/vnd.api+json", "Content-Type: application/vnd.api+json"})
    @POST("/dois")
    Call<JSONAPIDocument<Datacite42Schema>> create(@Body JSONAPIDocument<DoiSimplifiedModel> body);

    /**
     * Returns a doi.
     *
     * @param doi identifier
     * @return a doi
     */
    @Headers({"Accept: application/vnd.api+json"})
    @GET("/dois/{id}")
    Call<JSONAPIDocument<Datacite42Schema>> get(@Path("id") String doi);

    /**
     * Update a doi.
     *
     * @param doi  identifier
     * @param body data
     * @return HTTP 200
     */
    @Headers({"Accept: application/vnd.api+json", "Content-Type: application/vnd.api+json"})
    @PUT("/dois/{id}")
    Call<JSONAPIDocument<Datacite42Schema>> update(@Path("id") String doi, @Body JSONAPIDocument<DoiSimplifiedModel> body);

    /**
     * Delete a doi.
     *
     * @param doi identifier
     * @return HTTP 204
     */
    @Headers({"Accept: application/vnd.api+json"})
    @DELETE("/dois/{id}")
    Call<Void> delete(@Path("id") String doi);

}
