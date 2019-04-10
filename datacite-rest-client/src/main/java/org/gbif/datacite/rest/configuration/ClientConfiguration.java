package org.gbif.datacite.rest.configuration;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class contains some information to perform requests to services.
 * Primary information such as service URL, user credentials and
 * auxiliary technical data such as timeout time and cache size.
 */
public class ClientConfiguration implements Serializable {

    private static final long serialVersionUID = 7778374827110638452L;

    private final String baseApiUrl;

    private final Long timeOut;

    private final Long fileCacheMaxSizeMb;

    private final String user;

    private final String password;

    /**
     * Args constructor.
     * @param baseApiUrl service URL
     * @param timeOut timeout time in millisec
     * @param fileCacheMaxSizeMb cache file size in MB
     * @param user user
     * @param password password
     */
    private ClientConfiguration(String baseApiUrl, long timeOut, long fileCacheMaxSizeMb, String user, String password) {
        this.baseApiUrl = baseApiUrl;
        this.timeOut = timeOut;
        this.fileCacheMaxSizeMb = fileCacheMaxSizeMb;
        this.user = user;
        this.password = password;
    }

    public String getBaseApiUrl() {
        return baseApiUrl;
    }

    public Long getTimeOut() {
        return timeOut;
    }

    public Long getFileCacheMaxSizeMb() {
        return fileCacheMaxSizeMb;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientConfiguration that = (ClientConfiguration) o;
        return Objects.equals(timeOut, that.timeOut)
                && Objects.equals(fileCacheMaxSizeMb, that.fileCacheMaxSizeMb)
                && Objects.equals(baseApiUrl, that.baseApiUrl)
                && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseApiUrl, timeOut, fileCacheMaxSizeMb, user);
    }

    /**
     * Creates a new {@link Builder} instance.
     * @return a new builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String baseApiUrl;
        private Long timeOut = 60L;
        private Long fileCacheMaxSizeMb = 64L;
        private String user;
        private String password;

        /**
         * Hidden constructor to force use the containing class builder() method.
         */
        private Builder() {
            //DO NOTHING
        }


        public Builder withBaseApiUrl(String baseApiUrl) {
            this.baseApiUrl = baseApiUrl;
            return this;
        }

        public Builder withTimeOut(Long timeOut) {
            this.timeOut = timeOut;
            return this;
        }

        public Builder withFileCacheMaxSizeMb(Long fileCacheMaxSizeMb) {
            this.fileCacheMaxSizeMb = fileCacheMaxSizeMb;
            return this;
        }

        public Builder withUser(String user) {
            this.user = user;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public ClientConfiguration build() {
            return new ClientConfiguration(baseApiUrl, timeOut, fileCacheMaxSizeMb, user, password);
        }
    }
}
