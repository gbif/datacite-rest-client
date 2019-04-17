package org.gbif.datacite.rest.client.model;

/**
 * Represents event types which can be performed on the DOI.
 */
public enum EventType {

    /**
     * Register DOI.
     */
    REGISTER("register"),

    /**
     * Hide DOI.
     */
    HIDE("hide"),

    /**
     * Publish DOI.
     */
    PUBLISH("publish");

    /**
     * String value.
     */
    private String value;

    EventType(String event) {
    }

    public String getValue() {
        return value;
    }
}
