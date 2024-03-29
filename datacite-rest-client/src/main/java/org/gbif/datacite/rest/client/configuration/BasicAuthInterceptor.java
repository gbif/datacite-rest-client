package org.gbif.datacite.rest.client.configuration;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * DataCite requires privileges to perform create, update or delete operations.
 * This interceptor adds basic authorization mechanism.
 */
public class BasicAuthInterceptor implements Interceptor {

    private final String credentials;

    BasicAuthInterceptor(String user, String password) {
        this.credentials = Credentials.basic(user, password);
    }

    /**
     * Intercepts requests and adds the 'Authorization' header.
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials)
                .build();

        return chain.proceed(authenticatedRequest);
    }
}
