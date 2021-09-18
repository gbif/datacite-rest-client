package org.gbif.datacite.jsonapi.annotator;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMod;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@link JsonApiAnnotator} class.
 * Should check all the jsonapi-converter annotations were added.
 */
@RunWith(MockitoJUnitRunner.class)
public class JsonApiAnnotatorTest {

    @Mock
    JsonNode propertyNodeMock;

    // Class with property 'doi' should be annotated with the annotation 'Type'
    @Test
    public void classWithPropertyDoiShouldBeAnnotatedWithAnnotationType() throws Exception {
        // given
        JsonApiAnnotator jsonApiAnnotator = new JsonApiAnnotator();
        JCodeModel cm = new JCodeModel();
        JDefinedClass targetClass = cm._class("org.gbif.datacite.jsonapi.annotator.ApiAnnotatorTargetClass");
        JFieldVar doiField = targetClass.field(JMod.PRIVATE, String.class, "doi");

        assertTrue("Newly generated class should not have any annotations", targetClass.annotations().isEmpty());

        // when
        jsonApiAnnotator.propertyField(doiField, targetClass, "doi", propertyNodeMock);

        // then
        assertEquals("After method call should have 1 annotation", 1, targetClass.annotations().size());
    }

    // Property 'id' should be annotated with the annotation 'Id'
    @Test
    public void propertyIdShouldBeAnnotatedWithAnnotationId() throws Exception {
        // given
        JsonApiAnnotator jsonApiAnnotator = new JsonApiAnnotator();
        JCodeModel cm = new JCodeModel();
        JDefinedClass targetClass = cm._class("org.gbif.datacite.jsonapi.annotator.ApiAnnotatorTargetClass");
        JFieldVar idField = targetClass.field(JMod.PRIVATE, String.class, "id");

        assertTrue("Newly generated class should not have any annotations", targetClass.annotations().isEmpty());
        assertTrue("Newly generated field should not have any annotations", idField.annotations().isEmpty());

        // when
        jsonApiAnnotator.propertyField(idField, targetClass, "id", propertyNodeMock);

        // then
        assertEquals("After method call should have 1 annotation", 1, idField.annotations().size());
    }
}
