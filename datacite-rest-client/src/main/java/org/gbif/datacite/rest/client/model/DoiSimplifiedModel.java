package org.gbif.datacite.rest.client.model;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * For creation and/or updating DOI.
 * Annotated with {@link Type} for enabling JSON:API serialization.
 */
@Type("dois")
public class DoiSimplifiedModel {

    /**
     * Needed for JSON:API serialization.
     */
    @Id
    private String id;

    /**
     * DOI.
     * Required field for creation.
     */
    private String doi;

    /**
     * Event type.
     * If not specified during creation then the DOI will be created with the 'draft' status.
     * See {@link EventType}.
     */
    private String event;

    /**
     * XML metadata encoded with base64.
     */
    private String xml;

    /**
     * Metadata URL.
     */
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoiSimplifiedModel that = (DoiSimplifiedModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(doi, that.doi) &&
                Objects.equals(event, that.event) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doi, event, url);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DoiSimplifiedModel.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("doi='" + doi + "'")
                .add("event='" + event + "'")
                .add("xml='" + xml + "'")
                .add("url='" + url + "'")
                .toString();
    }
}
