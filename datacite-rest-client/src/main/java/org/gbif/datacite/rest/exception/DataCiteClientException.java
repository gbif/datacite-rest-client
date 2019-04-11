package org.gbif.datacite.rest.exception;

/**
 * Represents an exceptional case while interacting with a DataCite service.
 * See {@link org.gbif.datacite.rest.retrofit.SyncCall}
 * See {@link org.gbif.datacite.rest.client.retrofit.DataCiteRetrofitSyncClient}
 */
public class DataCiteClientException extends RuntimeException {

    /**
     * Common constructor.
     * @param ex exception
     */
    public DataCiteClientException(Throwable ex) {
    }
}
