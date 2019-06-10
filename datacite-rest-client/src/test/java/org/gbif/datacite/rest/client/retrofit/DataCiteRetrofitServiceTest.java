package org.gbif.datacite.rest.client.retrofit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.DeserializationFeature;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.commons.io.IOUtils;
import org.gbif.datacite.model.json.Datacite42Schema;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@link DataCiteRetrofitClient} methods.
 */
public class DataCiteRetrofitServiceTest {
    private static MockWebServer server;
    private static DataCiteRetrofitClient service;

    @BeforeClass
    public static void setup() throws IOException {
        // Setup server
        server = new MockWebServer();
        server.start();

        // Setup retrofit
        ObjectMapper objectMapper = new ObjectMapper();

        // exclude empty arrays from de/serialization
        objectMapper.configOverride(List.class).setInclude(
                JsonInclude.Value.construct(JsonInclude.Include.NON_EMPTY, null));
        objectMapper.configOverride(Set.class).setInclude(
                JsonInclude.Value.construct(JsonInclude.Include.NON_EMPTY, null));

        // allowing unknown field in section "include" JSON API
        ResourceConverter resourceConverter = new ResourceConverter(objectMapper, Datacite42Schema.class);
        resourceConverter.enableDeserializationOption(DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);

        JSONAPIConverterFactory converterFactory = new JSONAPIConverterFactory(resourceConverter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server.url("/").toString())
                .addConverterFactory(converterFactory)
                .build();

        service = retrofit.create(DataCiteRetrofitClient.class);
    }

    @AfterClass
    public static void destroy() throws IOException {
        server.shutdown();
    }

    // Test getting a single doi
    @Test
    public void testGetDoi() throws Exception {
        // given
        String response = IOUtils.toString(
                this.getClass().getResourceAsStream("/draftDoiResponse.json"),
                StandardCharsets.UTF_8);

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(response)
        );

        // when
        Response<JSONAPIDocument<Datacite42Schema>> actual = service.get("10.21373/110").execute();

        // then
        assertEquals(200, actual.code());
        assertNotNull(actual.body());

        Datacite42Schema datacite42Schema = actual.body().get();
        assertEquals("10.21373/110", datacite42Schema.getId());
        assertEquals("10.21373/110", datacite42Schema.getDoi());
    }

    // Test creating a new doi
    @Test
    public void testCreateDoi() throws IOException {
        // given
        String response = IOUtils.toString(
                this.getClass().getResourceAsStream("/draftDoiResponse.json"),
                StandardCharsets.UTF_8);

        server.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(response)
        );

        // when
        Response<JSONAPIDocument<Datacite42Schema>> actual = service.get("10.21373/110").execute();

        // then
        assertEquals(201, actual.code());
        assertNotNull(actual.body());

        Datacite42Schema datacite42Schema = actual.body().get();
        assertEquals("10.21373/110", datacite42Schema.getId());
        assertEquals("10.21373/110", datacite42Schema.getDoi());
    }
}
