package org.gbif.datacite.rest.client.retrofit;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "classpath:features/datacite_positive.feature",
        glue = "org.gbif.datacite.rest.client.retrofit",
        plugin = "pretty",
        monochrome = true
)
public class DataCiteRetrofitServiceIT {
}
