@DataCiteNegative
Feature: DataCite API negative cases
  Trying to call methods in a wrong way.
  We have a REST client with all the needed operations.

  Background:
    Given Rest client

  # Now API is not working properly: it returns 404 instead of 401. Scenario must be changed after their fixes.
  # Another strange detail: if the protocol http instead of https the answer is 200 (instead of 201 or exception)
  Scenario Template: Creation a DOI with wrong credentials should throw an exception
    Given Misconfigured rest client: wrong "<parameter>"
    And Model
    When Perform a request with a misconfigured client to DataCite's POST DOI
    Then Response message can be "<message>" or empty
    And Response code should be <code>

    Scenarios:
      | parameter                   | message   | code |
      | api (http instead of https) | OK        | 200  |
      | username                    | Not Found | 404  |
      | password                    | Not Found | 404  |
      | username and password       | Not Found | 404  |

  Scenario Template: Creation a DOI with wrong parameter <field>: <description>
    Given Model with wrong "<field>"
    When Perform a request to DataCite's POST DOI
    Then Response message can be "<message>" or empty
    And Response code should be <code>

    Scenarios:
      | field    | description               | message              | code |
      | URL      | invalid URL               | Unprocessable Entity | 422  |
      | metadata | random base64 encoded xml | Created              | 201  |
      | event    | random invalid event      | Created              | 201  |
      | doi      | invalid prefix            | Forbidden            | 403  |

  Scenario: Findable DOI can't be deleted
    Given An existing DOI with state "findable"
    When Perform a request to DataCite's DELETE DOI
    Then Response message (delete) can be "Method Not Allowed" or empty
    And Response code (delete) should be 405

  Scenario: Try create a DOI which already exists should throw an exception
    Given An existing DOI with state "findable"
    And Model with this DOI
    When Perform a request to DataCite's POST DOI
    Then Response message can be "Unprocessable Entity" or empty
    And Response code should be 422
