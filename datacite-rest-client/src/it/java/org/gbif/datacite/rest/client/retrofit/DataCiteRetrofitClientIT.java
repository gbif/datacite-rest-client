package org.gbif.datacite.rest.client.retrofit;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Provides integration tests for {@link DataCiteRetrofitSyncClient}.
 * Feature file is datacite_positive.feature (see resources/features).
 * Logic class {@link DataCiteRetrofitClientPositiveSteps}.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {
                "classpath:features/datacite_positive.feature",
                "classpath:features/datacite_negative.feature"
        },
        glue = "org.gbif.datacite.rest.client.retrofit",
        plugin = "pretty"
)
public class DataCiteRetrofitClientIT {
}
