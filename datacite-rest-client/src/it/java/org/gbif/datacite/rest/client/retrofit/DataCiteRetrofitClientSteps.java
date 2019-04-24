package org.gbif.datacite.rest.client.retrofit;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.IOUtils;
import org.gbif.datacite.model.json.Datacite42Schema;
import org.gbif.datacite.rest.client.configuration.ClientConfiguration;
import org.gbif.datacite.rest.client.model.DoiSimplifiedModel;
import retrofit2.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Logic class for {@link DataCiteRetrofitClientIT}.
 */
public class DataCiteRetrofitClientSteps {

    private static final ITPropertiesManager IT_PROPERTIES = ITPropertiesManager.getInstance();

    private static final String DOI_110 = "10.21373/110";
    private static final String DOI_105 = "10.21373/105";

    private DataCiteRetrofitSyncClient client;
    private Response<JSONAPIDocument<Datacite42Schema>> response;
    private Response<Void> deleteResponse;

    @Before("@DataCitePositive")
    public void prepare() {
        ClientConfiguration clientConfiguration = ClientConfiguration
                .builder()
                .withBaseApiUrl(IT_PROPERTIES.get("datacite.api.base.url"))
                .withUser(IT_PROPERTIES.get("datacite.user"))
                .withPassword(IT_PROPERTIES.get("datacite.password"))
                .build();

        client = new DataCiteRetrofitSyncClient(clientConfiguration);
    }

    @When("^Perform a request to DataCite's GET DOI by id$")
    public void performGetSingleDoi() {
        response = client.getDoi(DOI_110);
    }

    @Then("^Response status should be \"([^\"]*)\"$")
    public void checkResponseStatus(int status) {
        assertEquals(status, response.code());
    }

    @Then("^Response status should be \"([^\"]*)\" \\(no content\\)$")
    public void checkResponseStatusForDelete(int status) {
        assertEquals(status, deleteResponse.code());
    }

    @When("^Perform a request to DataCite's GET DOI$")
    public void performGetDoiList() {
        response = client.getDois();
    }

    @When("^Perform a request to DataCite's POST DOI$")
    public void performCreateDoi() throws IOException {
        DoiSimplifiedModel model = new DoiSimplifiedModel();
        model.setDoi(DOI_105);
        model.setId(DOI_105);
        String xmlMetadata = IOUtils.toString(
                this.getClass().getResourceAsStream("/datacite-example-full-v4.xml"),
                StandardCharsets.UTF_8);

        model.setXml(Base64.getEncoder().encodeToString(xmlMetadata.getBytes()));

        JSONAPIDocument<DoiSimplifiedModel> jsonApi = new JSONAPIDocument<>(model);

        response = client.createDoi(jsonApi);
    }

    @When("^Perform a request to DataCite's PUT DOI$")
    public void performUpdateDoi() throws IOException {
        DoiSimplifiedModel model = new DoiSimplifiedModel();
        model.setDoi(DOI_105);
        model.setId(DOI_105);
        String xmlMetadata = IOUtils.toString(
                this.getClass().getResourceAsStream("/datacite-example-full-v4.xml"),
                StandardCharsets.UTF_8);
        model.setXml(Base64.getEncoder().encodeToString(xmlMetadata.getBytes()));

        JSONAPIDocument<DoiSimplifiedModel> jsonApi = new JSONAPIDocument<>(model);

        response = client.updateDoi(DOI_105, jsonApi);
    }

    @When("^Perform a request to DataCite's DELETE DOI$")
    public void performDeleteDoi() {
        deleteResponse = client.deleteDoi(DOI_105);
    }
}
