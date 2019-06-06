package org.gbif.datacite.rest.client.configuration;

import org.gbif.datacite.rest.client.exception.DataCiteClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Utility class to perform synchronous call on Retrofit services.
 */
public class SyncCall {

    private static final Logger LOG = LoggerFactory.getLogger(SyncCall.class);

    /**
     * Private constructor.
     */
    private SyncCall() {
        //DO NOTHING
    }

    /**
     * Performs a synchronous call to {@link Call} instance.
     *
     * @param call to be executed
     * @param <T>  content of the response object
     * @return {@link Response} with content,
     * throws a {@link DataCiteClientException} when IOException was thrown from execute method
     */
    public static <T> Response<T> syncCallWithResponse(Call<T> call) {
        try {
            return call.execute();
        } catch (IOException ex) {
            LOG.error("Error executing call", ex);
            throw new DataCiteClientException(ex);
        }
    }
}
