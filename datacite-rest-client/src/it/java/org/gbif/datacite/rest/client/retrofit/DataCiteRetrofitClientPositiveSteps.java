package org.gbif.datacite.rest.client.retrofit;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.commons.io.IOUtils;
import org.gbif.datacite.rest.client.model.DoiSimplifiedModel;
import org.gbif.datacite.rest.client.model.EventType;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.gbif.datacite.rest.client.retrofit.DataCiteRetrofitClientCommonSteps.DOI_PREFIX;
import static org.gbif.datacite.rest.client.retrofit.DataCiteRetrofitClientCommonSteps.TEST_URL;
import static org.gbif.datacite.rest.client.retrofit.DataCiteRetrofitClientCommonSteps.client;
import static org.gbif.datacite.rest.client.retrofit.DataCiteRetrofitClientCommonSteps.currentDoi;
import static org.gbif.datacite.rest.client.retrofit.DataCiteRetrofitClientCommonSteps.deleteResponse;
import static org.gbif.datacite.rest.client.retrofit.DataCiteRetrofitClientCommonSteps.model;
import static org.gbif.datacite.rest.client.retrofit.DataCiteRetrofitClientCommonSteps.response;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Logic class for {@link DataCiteRetrofitClientIT} for positive cases.
 */
public class DataCiteRetrofitClientPositiveSteps {

    @Given("^Model with provided XML metadata$")
    public void modelWithXmlMetadata() throws Exception {
        String doi = TestDoiProvider.get(DOI_PREFIX);
        model = new DoiSimplifiedModel();
        model.setDoi(doi);
        model.setId(doi);
        String xmlMetadata = IOUtils.toString(
                this.getClass().getResourceAsStream("/datacite-example-full-v4.xml"),
                StandardCharsets.UTF_8);
        model.setXml(Base64.getEncoder().encodeToString(xmlMetadata.getBytes(StandardCharsets.UTF_8)));
        model.setUrl(TEST_URL);
    }

    @And("^event type is \"([^\"]*)\"$")
    public void setEventType(EventType eventType) {
        model.setEvent(eventType.getValue());
    }


    @When("^Perform a request to DataCite's GET DOI by id$")
    public void performGetSingleDoi() {
        assertNotNull(response.body());
        response = client.getDoi(response.body().get().getId());
    }

    @And("^State should be \"([^\"]*)\"$")
    public void checkState(String state) {
        assertNotNull(response.body());
        assertEquals(state, response.body().get().getState());
    }

    @When("^Perform a request to DataCite's GET DOI$")
    public void performGetDoiList() {
        response = client.getDois();
    }

    @When("^Perform a request to DataCite's POST DOI$")
    public void performCreateDoi() {
        JSONAPIDocument<DoiSimplifiedModel> jsonApi = new JSONAPIDocument<>(model);

        response = client.createDoi(jsonApi);
    }

    @When("^Perform a request to DataCite's PUT DOI$")
    public void performUpdateDoi() {
        JSONAPIDocument<DoiSimplifiedModel> jsonApi = new JSONAPIDocument<>(model);

        response = client.updateDoi(currentDoi, jsonApi);
    }

    @When("^Perform a request to DataCite's DELETE DOI$")
    public void performDeleteDoi() {
        assertNotNull(response.body());
        deleteResponse = client.deleteDoi(response.body().get().getDoi());
    }
}
