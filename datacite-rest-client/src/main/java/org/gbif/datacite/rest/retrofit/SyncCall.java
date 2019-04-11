package org.gbif.datacite.rest.retrofit;

import org.gbif.datacite.rest.exception.DataCiteClientException;
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
     *
     * @param call to be executed
     * @param <T>  content of the response object
     * @return {@link Response} with content,
     * throws an {@link HttpException} when response is bad:
     * - response is successful, but body is null
     * - response is successful, body is null but status is not 204
     * - response is not successful
     * throws a {@link DataCiteClientException} when IOException was thrown from execute method
     */
    public static <T> Response<T> syncCallWithResponse(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful() && (response.body() != null || response.code() == 204)) {
                return response;
            }
            LOG.debug("Service responded with an error {}", response);
            throw new HttpException(response); // Propagates the failed response
        } catch (IOException ex) {
            LOG.error("Error executing call", ex);
            throw new DataCiteClientException(ex);
        }
    }
}
