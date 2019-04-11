package org.gbif.datacite.rest.client.retrofit;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Provides integration tests for {@link DataCiteRetrofitSyncClient}.
 * Feature file is datacite_positive.feature (see resources/features).
 * Logic class {@link DataCiteRetrofitClientSteps}.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "classpath:features/datacite_positive.feature",
        glue = "org.gbif.datacite.rest.client.retrofit",
        plugin = "pretty",
        monochrome = true
)
public class DataCiteRetrofitClientIT {
}
