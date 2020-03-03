[![Build Status](https://builds.gbif.org/job/datacite-rest-client/badge/icon?plastic)](https://builds.gbif.org/job/datacite-rest-client/)
[![Quality Gate Status](https://sonar.gbif.org/api/project_badges/measure?project=org.gbif.datacite%3Adatacite-rest-client-parent&metric=alert_status)](https://sonar.gbif.org/dashboard?id=org.gbif.datacite%3Adatacite-rest-client-parent) 
[![Coverage](https://sonar.gbif.org/api/project_badges/measure?project=org.gbif.datacite%3Adatacite-rest-client-parent&metric=coverage)](https://sonar.gbif.org/dashboard?id=org.gbif.datacite%3Adatacite-rest-client-parent)

# DataCite rest client

This library provides a simple implementation of a Java client for the REST Datacite API.
Initially it will provide the basic DOI operations described [here](https://support.datacite.org/reference/dois-2) to perform CRUD and query operations on DOIs.

## References
 - Datacite REST API Guide: [https://support.datacite.org/docs/api](https://support.datacite.org/docs/api) and [https://support.datacite.org/reference](https://support.datacite.org/reference).
 - Datacite supported metadata schemas: [https://schema.datacite.org/](https://schema.datacite.org/) and Github repo [https://github.com/datacite/schema](https://github.com/datacite/schema).
 - [Datacite testing guide](https://support.datacite.org/docs/testing-guide)
 - Datacite DOI Fabrica: [test](https://doi.test.datacite.org), [prod](https://doi.datacite.org)
 - [Digital object identifier (DOI)](https://en.wikipedia.org/wiki/Digital_object_identifier)
 
## Modules
 Consists of two modules:
 - [**datacite-jsonapi-annotator**](datacite-jsonapi-annotator/README.md)
 - [**datacite-rest-client**](datacite-rest-client/README.md)
 
## Dependencies
The implementation of this library will depend on the following commonly used libraries:
 - [Retrofit](https://square.github.io/retrofit/) and [OkHtpp](https://square.github.io/okhttp/) as HTTP clients.
 - [Jackson](https://github.com/FasterXML/jackson) for JSON data handling.
 - [SLF4J](https://www.slf4j.org/) as logging facade.
 - [error-prone](https://github.com/google/error-prone) as static analysis tool.
 - Testing: [JUnit 5](https://junit.org/junit5/), 
 [Mockito](https://site.mockito.org/), 
 [Cucumber](https://docs.cucumber.io/).
 
See [pom.xml](pom.xml) for more details.
 
## DOI prefixes and usage

| Environment | Prefix   | Usage                                           | Exceptions |
|-------------|----------|-------------------------------------------------|------------|
| Production  | 10.15468 | Datasets and downloads (automatically assigned) | some manually assigned ([see API](https://api.datacite.org/dois?prefix=10.15468&resource-type-id=text)) |
| Production  | 10.15469 | Previously used for testing                     |            |
| Production  | 10.26161 | Tensorflow models (manually assigned)           |            |
| Production  | 10.35000 | Custom data exports (manually assigned)         |            |
| Production  | 10.35035 | Documentation (manually assigned)               |            |
| Test        | 10.21373 | Datasets and downloads (automatically assigned) |            |
| Test        | 10.15468 | Previously used for testing                     |            |
| Test        | 10.21374 | Misc. testing                                   |            |


