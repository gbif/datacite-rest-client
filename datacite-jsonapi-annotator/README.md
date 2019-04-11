# datacite-jsonapi-annotator

This module contains only one class [JsonApiAnnotator](src/main/java/org/gbif/datacite/jsonapi/annotator/JsonApiAnnotator.java). 
That was moved to the separate module because of jsonschema2pojo-maven-plugin requires a standalone dependency to 
enable annotating with customAnnotator. 
See the plugin configuration in datacite-rest-client-parent pom dependencyManagement section.

JsonApiAnnotator adds some annotation from com.github.jasminb:jsonapi-converter to enable handle generated model as JSON:API.

[Parent](../README.md)


