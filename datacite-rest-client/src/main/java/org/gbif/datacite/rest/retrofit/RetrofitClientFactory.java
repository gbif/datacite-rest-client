package org.gbif.datacite.rest.retrofit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.DeserializationFeature;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.gbif.datacite.model.json.Datacite42Schema;
import org.gbif.datacite.rest.configuration.ClientConfiguration;
import org.gbif.datacite.rest.util.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Factory class for okHttp and retrofit clients.
 */
public final class RetrofitClientFactory {

    private static final Logger LOG = LoggerFactory.getLogger(RetrofitClientFactory.class);
    private static final PropertiesManager APP_PROPERTIES = PropertiesManager.getInstance();

    private RetrofitClientFactory() {
    }

    /**
     * Creates a {@link OkHttpClient} with a {@link Cache} from a specific {@link ClientConfiguration}.
     */
    public static OkHttpClient createClient(ClientConfiguration config) {

        // for logging HTTP requests details
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.valueOf(APP_PROPERTIES.get("logging.interceptor.level")));

        OkHttpClient.Builder clientBuilder =
                new OkHttpClient.Builder()
                        // test user: GBIF.GBIF password: J9jotXAq8fdinDH2
                        .addInterceptor(new BasicAuthInterceptor(config.getUser(), config.getPassword()))
                        .addInterceptor(loggingInterceptor)
                        .connectTimeout(config.getTimeOut(), TimeUnit.SECONDS)
                        .readTimeout(config.getTimeOut(), TimeUnit.SECONDS);

        clientBuilder.cache(createCache(config.getFileCacheMaxSizeMb()));

        // create the client and return it
        return clientBuilder.build();
    }

    /**
     * Creates retrofit client.
     * Some specific details were configured in order to process JSON API.
     *
     * @param clientConfiguration client properties (baseApiUrl etc.)
     * @param serviceClass        service class
     * @param <S>                 type
     * @return retrofit client
     */
    public static <S> S createRetrofitClient(ClientConfiguration clientConfiguration, Class<S> serviceClass) {

        ObjectMapper objectMapper = new ObjectMapper();

        // exclude empty arrays from de/serialization
        objectMapper.configOverride(List.class).setInclude(
                JsonInclude.Value.construct(JsonInclude.Include.NON_EMPTY, null));
        objectMapper.configOverride(Set.class).setInclude(
                JsonInclude.Value.construct(JsonInclude.Include.NON_EMPTY, null));

        // allowing unknown field in section "include" JSON API
        ResourceConverter resourceConverter = new ResourceConverter(objectMapper, Datacite42Schema.class);
        resourceConverter.enableDeserializationOption(DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);

        JSONAPIConverterFactory jsonapiConverterFactory = new JSONAPIConverterFactory(resourceConverter);

        // Create service. Use ConverterFactory for converting JSON API
        return new Retrofit.Builder()
                .client(createClient(clientConfiguration))
                .baseUrl(clientConfiguration.getBaseApiUrl())
                .addConverterFactory(jsonapiConverterFactory)
                .validateEagerly(true)
                .build()
                .create(serviceClass);
    }

    /**
     * Creates a Cache using a maximum size.
     *
     * @param maxSize of the file cache in MB
     * @return a new instance of file based cache
     */
    private static Cache createCache(long maxSize) {

        try {
            // create cache file
            File httpCacheDirectory;
            // use a new file cache for the current session
            String cacheName = System.currentTimeMillis() + "-wsCache";
            httpCacheDirectory = Files.createTempDirectory(cacheName).toFile();
            httpCacheDirectory.deleteOnExit();
            LOG.info("Cache file created - {}", httpCacheDirectory.getAbsolutePath());
            // create cache
            return new Cache(httpCacheDirectory, maxSize);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Cannot run without the ability to create temporary cache directory", e);
        }
    }
}
