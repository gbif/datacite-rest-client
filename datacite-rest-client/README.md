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
 
 As an alternative to providing all metadata directly in JSON format, you can also provide metadata in other formats, in particular of course DataCite XML. You do this by base64-encoding the metadata and including them in the xml attribute. For example:
 ```json
 {
  "data": {
    "attributes": {
      "event": "publish",
      "doi": "10.21373/100",
      "url": "https://schema.datacite.org/meta/kernel-4.0/index.html",
      "xml": "<base64_encoded_xml_metadata>"
  }
}
 ```
 All the fields are required otherwise DOI will be created with a 'draft' state.
 
 Prefix 10.21373 should be used on the test environment.
 To perform some operations (POST, PUT, DELETE) additional rights are required - Client ID and password.
 
 Important: only draft DOI can be deleted.
 
[Parent](../README.md)
