package org.gbif.datacite.rest.retrofit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.HttpException;
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
     * @param call to be executed
     * @param <T> content of the response object
     * @return the content of the response, throws an {@link HttpException} in case of error
     */
    public static <T> T syncCall(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                return response.body();
            }
            LOG.debug("Service responded with an error {}", response);
            throw new HttpException(response); // Propagates the failed response
        } catch (IOException ex) {
            LOG.error("Error executing call", ex);
            throw new RuntimeException(ex);
        }
    }
}
