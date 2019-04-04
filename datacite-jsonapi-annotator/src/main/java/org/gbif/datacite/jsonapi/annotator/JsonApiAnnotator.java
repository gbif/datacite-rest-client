package org.gbif.datacite.jsonapi.annotator;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import org.jsonschema2pojo.AbstractAnnotator;

/**
 * Used for adding JSON API specific annotation to generated model by json schema.
 */
public class JsonApiAnnotator extends AbstractAnnotator {

    /**
     * Adds annotations to a model class.
     * Model to be processed as JSON API should contain {@link Type} annotation with parameter
     * and {@link Id} annotation as well.
     *
     * @param field        processed field
     * @param clazz        processed class
     * @param propertyName property name
     * @param propertyNode property node
     */
    @Override
    public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
        super.propertyField(field, clazz, propertyName, propertyNode);

        if (propertyName.equals("doi")) {
            clazz.annotate(Type.class).param("value", "dois");
        }

        if (propertyName.equals("id")) {
            field.annotate(Id.class);
        }
    }
}
