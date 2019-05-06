@DataCiteNegative
Feature: Check negative cases using DataCite API
  Trying to call methods in a wrong way.
  We have a REST client with all the needed operations.

  Background:
    Given Rest client

  # Now API is not working properly: it returns 404 instead of 401. Scenario must be changed after their fixes.
  # Another strange detail: if the protocol http instead of https the answer is 200 (instead of 201 or exception)
  Scenario Template: Creation a DOI with wrong credentials should throw an exception
    Given Misconfigured rest client: wrong "<parameter>"
    And Model
    When Perform a request with a misconfigured client to DataCite's POST DOI with exception handling
    Then Response should be "<message>"

    Scenarios:
      | parameter                   | message            |
      | api (http instead of https) | OK                 |
      | username                    | HTTP 404 Not Found |
      | password                    | HTTP 404 Not Found |
      | username and password       | HTTP 404 Not Found |

  Scenario Template: Creation a DOI with wrong parameter <field>: <description>
    Given Model with wrong "<field>"
    When Perform a request to DataCite's POST DOI with exception handling
    Then Response should be "<message>"

    Scenarios:
      | field    | description               | message                       |
      | URL      | invalid URL               | HTTP 422 Unprocessable Entity |
      | metadata | random base64 encoded xml | Created                       |
      | event    | random invalid event      | Created                       |
      | doi      | invalid prefix            | HTTP 403 Forbidden            |

  Scenario: Findable DOI can't be deleted
    Given An existing DOI with state "findable"
    When Perform a request to DataCite's DELETE DOI with exception handling
    Then Response should be "HTTP 405 Method Not Allowed"

  Scenario: Try create a DOI which already exists should throw an exception
    Given An existing DOI with state "findable"
    And Model with this DOI
    When Perform a request to DataCite's POST DOI with exception handling
    Then Response should be "HTTP 422 Unprocessable Entity"
