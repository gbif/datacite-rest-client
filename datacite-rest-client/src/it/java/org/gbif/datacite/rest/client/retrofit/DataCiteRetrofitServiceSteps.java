package org.gbif.datacite.rest.client.retrofit;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.gbif.datacite.model.json.Datacite42Schema;
import org.gbif.datacite.rest.configuration.ClientConfiguration;
import org.gbif.datacite.rest.retrofit.RetrofitClientFactory;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataCiteRetrofitServiceSteps {

    private static final ITPropertiesManager IT_PROPERTIES = ITPropertiesManager.getInstance();

    private static final String DOI_110 = "10.21373/110";
    private static final String DOI_105 = "10.21373/105";

    private DataCiteRetrofitService service;
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

        service = RetrofitClientFactory.createRetrofitClient(clientConfiguration, DataCiteRetrofitService.class);
    }

    @When("^Perform a request to DataCite's GET DOI by id$")
    public void performGetSingleDoi() throws IOException {
        response = service.get(DOI_110).execute();
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
    public void performGetDoiList() throws IOException {
        response = service.get().execute();
    }

    @When("^Perform a request to DataCite's POST DOI$")
    public void performCreateDoi() throws IOException {
        Datacite42Schema datacite42Schema = new Datacite42Schema();
        datacite42Schema.setId(DOI_105);
        datacite42Schema.setDoi(DOI_105);


        JSONAPIDocument<Datacite42Schema> jsonapi = new JSONAPIDocument<>(datacite42Schema);

        response = service.create(jsonapi).execute();
    }

    @When("^Perform a request to DataCite's PUT DOI$")
    public void performUpdateDoi() throws IOException {
        Datacite42Schema datacite42Schema = new Datacite42Schema();
        datacite42Schema.setId(DOI_105);
        datacite42Schema.setDoi(DOI_105);
        datacite42Schema.setPublicationYear("2019");

        JSONAPIDocument<Datacite42Schema> jsonapi = new JSONAPIDocument<>(datacite42Schema);

        response = service.update(DOI_105, jsonapi).execute();
    }

    @When("^Perform a request to DataCite's DELETE DOI$")
    public void performDeleteDoi() throws IOException {
        deleteResponse = service.delete(DOI_105).execute();
    }

}
