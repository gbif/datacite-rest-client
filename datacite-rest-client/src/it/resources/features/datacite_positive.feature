@DataCitePositive
Feature: DataCite API positive cases
  Try to check all the CRUD operations of DataCite API. Positive cases.
  We have a REST client with all the needed operations.

  Background:
    Given Rest client

  Scenario: Create, get and delete a DOI
    Given Model with provided XML metadata
    When Perform a request to DataCite's POST DOI
    Then Response code should be 201
    When Perform a request to DataCite's GET DOI by id
    Then Response code should be 200
    When Perform a request to DataCite's GET metadata by id
    Then Response code should be 200
    And Metadata should be valid
    When Perform a request to DataCite's DELETE DOI
    Then Response code (delete) should be 204

  Scenario Template: Create a new <state> DOI by XML metadata
    Given Model with provided XML metadata
    And event type is <event>
    When Perform a request to DataCite's POST DOI
    Then Response code should be 201
    And State should be "<state>"

    Scenarios:
      | event    | state      |
      | DRAFT    | draft      |
      | REGISTER | registered |
      | PUBLISH  | findable   |

  Scenario Template: Change state of an existing DOI
    Given An existing DOI with state "<sourceState>"
    And event type is <event>
    When Perform a request to DataCite's PUT DOI
    Then Response code should be 200
    And State should be "<nextState>"

    Scenarios:
      | event    | sourceState | nextState  |
      | REGISTER | draft       | registered |
      | PUBLISH  | draft       | findable   |
      | PUBLISH  | registered  | findable   |
      | HIDE     | findable    | registered |
