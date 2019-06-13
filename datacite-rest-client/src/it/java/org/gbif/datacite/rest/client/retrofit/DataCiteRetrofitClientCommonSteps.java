package org.gbif.datacite.rest.client.retrofit;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.io.IOUtils;
import org.gbif.datacite.model.json.Datacite42Schema;
import org.gbif.datacite.rest.client.configuration.ClientConfiguration;
import org.gbif.datacite.rest.client.model.DoiSimplifiedModel;
import org.gbif.datacite.rest.client.model.EventType;
import retrofit2.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Shared logic class for {@link DataCiteRetrofitClientIT}.
 */
public class DataCiteRetrofitClientCommonSteps {

    private static final ITPropertiesManager IT_PROPERTIES = ITPropertiesManager.getInstance();
    public static final String TEST_URL = "https://schema.datacite.org/meta/kernel-4.0/index.html";
    public static final String DOI_PREFIX = IT_PROPERTIES.get("datacite.doi.prefix");
    public static DataCiteRetrofitSyncClient client;
    public static Response<JSONAPIDocument<Datacite42Schema>> response;
    public static Response<Void> deleteResponse;
    public static DoiSimplifiedModel model;
    public static String currentDoi;
    public static String actualMetadata;

    @Given("^Rest client$")
    public void restClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration
                .builder()
                .withBaseApiUrl(IT_PROPERTIES.get("datacite.api.base.url"))
                .withUser(IT_PROPERTIES.get("datacite.user"))
                .withPassword(IT_PROPERTIES.get("datacite.password"))
                .build();

        client = new DataCiteRetrofitSyncClient(clientConfiguration);
    }

    @Given("^An existing DOI with state \"([^\"]*)\"$")
    public void createDoiWithState(String state) throws IOException {
        currentDoi = TestDoiProvider.get(DOI_PREFIX);
        model = new DoiSimplifiedModel();
        model.setDoi(currentDoi);
        model.setId(currentDoi);
        String xmlMetadata = IOUtils.toString(
                this.getClass().getResourceAsStream("/datacite-example-full-v4.xml"),
                StandardCharsets.UTF_8);
        model.setXml(Base64.getEncoder().encodeToString(xmlMetadata.getBytes(StandardCharsets.UTF_8)));
        model.setUrl(TEST_URL);
        model.setEvent(EventType.ofState(state).getValue());

        JSONAPIDocument<DoiSimplifiedModel> jsonApi = new JSONAPIDocument<>(model);

        response = client.createDoi(jsonApi);
        assertNotNull(response.body());
        assertEquals(state, response.body().get().getState());
        model = new DoiSimplifiedModel();
    }

    @Then("^Response message should be \"([^\"]*)\"$")
    public void checkResponseMessage(String message) {
        assertEquals(message, response.message());
    }

    @And("^Response code should be \"([^\"]*)\"$")
    public void checkResponseCode(int code) {
        assertEquals(code, response.code());
    }

    @Then("^Response message \\(delete\\) should be \"([^\"]*)\"$")
    public void checkResponseMessageDelete(String message) {
        assertEquals(message, deleteResponse.message());
    }

    @And("^Response code \\(delete\\) should be \"([^\"]*)\"$")
    public void checkResponseCodeDelete(int code) {
        assertEquals(code, deleteResponse.code());
    }
}
