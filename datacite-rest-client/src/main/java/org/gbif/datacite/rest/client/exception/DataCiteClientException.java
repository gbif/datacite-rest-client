package org.gbif.datacite.rest.client.exception;

import org.gbif.datacite.rest.client.configuration.SyncCall;
import org.gbif.datacite.rest.client.retrofit.DataCiteRetrofitSyncClient;

/**
 * Represents an exceptional case while interacting with a DataCite service.
 * See {@link SyncCall}
 * See {@link DataCiteRetrofitSyncClient}
 */
public class DataCiteClientException extends RuntimeException {

    /**
     * Common constructor.
     * @param ex exception
     */
    public DataCiteClientException(Throwable ex) {
    }
}
