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
     * Get a list of dois.
     *
     * @return a list of dois
     */
    @Headers({"Accept: application/vnd.api+json"})
    @GET("/dois")
    Call<JSONAPIDocument<Datacite42Schema>> get();

    /**
     * Create doi with body.
     *
     * @param body data
     * @return HTTP 201
     */
    @Headers({"Accept: application/vnd.api+json", "Content-Type: application/vnd.api+json"})
    @POST("/dois")
    Call<JSONAPIDocument<Datacite42Schema>> create(@Body JSONAPIDocument<DoiSimplifiedModel> body);

    /**
     * Get doi by id.
     *
     * @param doi identifier
     * @return a doi
     */
    @Headers({"Accept: application/vnd.api+json"})
    @GET("/dois/{id}")
    Call<JSONAPIDocument<Datacite42Schema>> get(@Path("id") String doi);

    /**
     * Update doi with body by id.
     *
     * @param doi  identifier
     * @param body data
     * @return an updated doi
     */
    @Headers({"Accept: application/vnd.api+json", "Content-Type: application/vnd.api+json"})
    @PUT("/dois/{id}")
    Call<JSONAPIDocument<Datacite42Schema>> update(@Path("id") String doi, @Body JSONAPIDocument<DoiSimplifiedModel> body);

    /**
     * Delete doi by id.
     *
     * @param doi identifier
     * @return HTTP 204
     */
    @Headers({"Accept: application/vnd.api+json"})
    @DELETE("/dois/{id}")
    Call<Void> delete(@Path("id") String doi);

    /**
     * Get metadata by doi.
     *
     * @param doi identifier
     * @return doi XML metadata
     */
    @Headers({"Accept: application/vnd.datacite.datacite+xml"})
    @GET("/dois/{id}")
    Call<String> getMetadata(@Path("id") String doi);
}
