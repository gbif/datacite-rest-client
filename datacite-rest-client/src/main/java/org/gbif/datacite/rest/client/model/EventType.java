package org.gbif.datacite.rest.client.model;

/**
 * Represents event types which can be performed on the DOI.
 * Event type can be omitted then DOI will be created with the state 'draft'.
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
    PUBLISH("publish"),

    /**
     * Can be omitted.
     */
    DRAFT(null);

    /**
     * String value.
     */
    private final String value;

    EventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
