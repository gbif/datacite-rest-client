package org.gbif.datacite.rest.client.retrofit;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import org.gbif.datacite.model.json.Datacite42Schema;
import org.gbif.datacite.rest.client.DataCiteService;
import org.gbif.datacite.rest.configuration.ClientConfiguration;
import org.gbif.datacite.rest.retrofit.RetrofitClientFactory;

import static org.gbif.datacite.rest.retrofit.SyncCall.syncCall;

/**
 * Represents an {@link DataCiteService} synchronous client.
 * It wraps a Retrofit client to perform the actual calls.
 */
public class DataCiteRetrofitSyncClient implements DataCiteService {

    private final DataCiteRetrofitService dataCiteRetrofitService;

    /**
     * Creates an instance using the provided configuration settings.
     *
     * @param clientConfiguration Rest client configuration
     */
    public DataCiteRetrofitSyncClient(ClientConfiguration clientConfiguration) {
        dataCiteRetrofitService = RetrofitClientFactory.createRetrofitClient(clientConfiguration,
                DataCiteRetrofitService.class);
    }

    /**
     * Performs a synchronous call to the DataCite service's get.
     *
     * @return a list of dois wrapped by {@link Datacite42Schema}
     */
    @Override
    public JSONAPIDocument<Datacite42Schema> getDois() {
        return syncCall(dataCiteRetrofitService.get());
    }

    /**
     * Performs a synchronous call to the DataCite service's create.
     *
     * @param body data
     * @return created object in JSON API format
     */
    @Override
    public JSONAPIDocument<Datacite42Schema> createDoi(JSONAPIDocument<Datacite42Schema> body) {
        return syncCall(dataCiteRetrofitService.create(body));
    }

    /**
     * Performs a synchronous call to the DataCite service's get.
     *
     * @param doi identifier
     * @return a doi wrapped by {@link Datacite42Schema}
     */
    @Override
    public JSONAPIDocument<Datacite42Schema> getDoi(String doi) {
        return syncCall(dataCiteRetrofitService.get(doi));
    }

    /**
     * Performs a synchronous call to the DataCite service's update.
     *
     * @param doi  identifier
     * @param body data
     * @return updated object in JSON API format
     */
    @Override
    public JSONAPIDocument<Datacite42Schema> updateDoi(String doi, JSONAPIDocument<Datacite42Schema> body) {
        return syncCall(dataCiteRetrofitService.update(doi, body));
    }

    /**
     * Performs a synchronous call to the DataCite service's delete.
     *
     * @param doi identifier
     */
    @Override
    public void deleteDoi(String doi) {
        syncCall(dataCiteRetrofitService.delete(doi));
    }

}
