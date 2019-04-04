@DataCitePositive
Feature: Check positive cases using DataCite API
  Try to check all the CRUD operations of DataCite API. Positive cases.
  We have a rest client with all the needed operations.

  @DataCiteGetSingleDoiPositive
  Scenario: Get a single DOI from DataCite API
    When Perform a request to DataCite's GET DOI by id
    Then Response status should be "200"

  @DataCiteGetListDoisPositive
  Scenario: Get a list of DOIs from DataCite API
    When Perform a request to DataCite's GET DOI
    Then Response status should be "200"

  @DataCiteCreateDoiPositive
  Scenario: Create a new DOI with DataCite API
    When Perform a request to DataCite's POST DOI
    Then Response status should be "201"

  @DataCiteUpdateDoiPositive
  Scenario: Update an existing DOI with DataCite API
    When Perform a request to DataCite's PUT DOI
    Then Response status should be "200"

  @DataCiteDeleteDoiPositive
  Scenario: Delete an existing DOI with DataCite API
    When Perform a request to DataCite's DELETE DOI
    Then Response status should be "204" (no content)
