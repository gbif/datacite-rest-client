package org.gbif.datacite.rest.client.retrofit;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import org.gbif.datacite.model.json.Datacite42Schema;
import org.gbif.datacite.rest.client.DataCiteClient;
import org.gbif.datacite.rest.client.configuration.ClientConfiguration;
import org.gbif.datacite.rest.client.configuration.RetrofitClientFactory;
import org.gbif.datacite.rest.client.model.DoiSimplifiedModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import static org.gbif.datacite.rest.client.configuration.SyncCall.syncCallWithResponse;

/**
 * Represents an {@link DataCiteClient} synchronous client.
 * It wraps a Retrofit client to perform the actual calls.
 */
public class DataCiteRetrofitSyncClient implements DataCiteClient {

    private static final Logger LOG = LoggerFactory.getLogger(DataCiteRetrofitSyncClient.class);

    private static final int RETRY = 5;

    private final DataCiteRetrofitClient dataCiteRetrofitService;

    /**
     * Creates an instance using the provided configuration settings.
     *
     * @param clientConfiguration Rest client configuration
     */
    public DataCiteRetrofitSyncClient(ClientConfiguration clientConfiguration) {
        dataCiteRetrofitService = RetrofitClientFactory.createRetrofitClient(clientConfiguration,
                DataCiteRetrofitClient.class);
    }

    /**
     * Performs a synchronous call to the DataCite service's get (with XML metadata content).
     *
     * @param doi identifier
     * @return doi XML metadata wrapped by {@link Response}
     */
    @Override
    public Response<String> getMetadata(String doi) {
        return syncCallWithResponse(dataCiteRetrofitService.getMetadata(doi));
    }

    /**
     * Performs a synchronous call to the DataCite service's get.
     *
     * @return a list of dois wrapped by {@link Datacite42Schema}
     */
    @Override
    public Response<JSONAPIDocument<Datacite42Schema>> getDois() {
        return syncCallWithResponse(dataCiteRetrofitService.get());
    }

    /**
     * Performs a synchronous call to the DataCite service's create.
     *
     * @param body data
     * @return created object in JSON API format wrapped by {@link Response}
     */
    @Override
    public Response<JSONAPIDocument<Datacite42Schema>> createDoi(JSONAPIDocument<DoiSimplifiedModel> body) {
        final Response<JSONAPIDocument<Datacite42Schema>> response = syncCallWithResponse(dataCiteRetrofitService.create(body));

        // DataCite can return 201 but the DOI may not created yet
        Response<JSONAPIDocument<Datacite42Schema>> anotherResponse;

        for (long i = 0; i < RETRY; i++) {
            anotherResponse = getDoi(body.get().getDoi());
            if (anotherResponse.code() == 404) {
                sleep(500 + i * 1000);
            } else {
                break;
            }
        }

        return response;
    }

    /**
     * Performs a synchronous call to the DataCite service's get.
     *
     * @param doi identifier
     * @return a doi wrapped by {@link Datacite42Schema} and {@link Response}
     */
    @Override
    public Response<JSONAPIDocument<Datacite42Schema>> getDoi(String doi) {
        return syncCallWithResponse(dataCiteRetrofitService.get(doi));
    }

    /**
     * Performs a synchronous call to the DataCite service's update.
     *
     * @param doi  identifier
     * @param body data
     * @return updated object in JSON API format wrapped by {@link Response}
     */
    @Override
    public Response<JSONAPIDocument<Datacite42Schema>> updateDoi(String doi, JSONAPIDocument<DoiSimplifiedModel> body) {
        return syncCallWithResponse(dataCiteRetrofitService.update(doi, body));
    }

    /**
     * Performs a synchronous call to the DataCite service's delete.
     *
     * @param doi identifier
     * @return {@link Response} with no content
     */
    @Override
    public Response<Void> deleteDoi(String doi) {
        return syncCallWithResponse(dataCiteRetrofitService.delete(doi));
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            LOG.info("Interrupted");
        }
    }
}
