# datacite-rest-client

An implementation of a Java client for the REST Datacite API.

 ## JSON:API
 API works through [JSON:API](https://jsonapi.org/) format 
 and uses java implementation [jsonapi-converter](https://github.com/jasminb/jsonapi-converter).
 
 ## Create a new DOI
 There are three type of DOI: draft, registered and findable.
 There are three actions ('event' field) can be performed over DOI:
 - register (move from 'draft' to 'registered')
 - publish (move from 'draft' or 'registered' to 'findable')
 - hide (move from 'findable' to 'registered')
 
 The field 'state' represent the current state of the DOI.
 
 To create a new draft DOI only 'doi' field is needed.
 
 To create a findable DOI the following fields must be provided:
 - doi
 - creators
 - titles
 - publisher
 - publicationYear
 - resourceType
 
 Example of the json:
 ```json
 {
   "data": {
     "id": "10.21373/100",
     "type": "dois",
     "attributes": {
       "doi": "10.21373/100",
       "creators": [
         {
           "name": "DataCite Metadata Working Group"
         }
       ],
       "titles": [
         {
           "title": "DataCite Metadata Schema Documentation for the Publication and Citation of Research Data v4.0"
         }
       ],
       "publisher": "DataCite e.V.",
       "types": {
         "resourceTypeGeneral": "Text"
       },
       "publicationYear": 2016,
       "url": "https://schema.datacite.org/meta/kernel-4.0/index.html",
       "schemaVersion": "http://datacite.org/schema/kernel-4"
     }
   }
 }
 ```
 
 Prefix 10.21373 should be used on the test environment.
 To perform some operations (POST, PUT, DELETE) additional rights are required - Client ID and password.
 
 Important: only draft DOI can be deleted.
