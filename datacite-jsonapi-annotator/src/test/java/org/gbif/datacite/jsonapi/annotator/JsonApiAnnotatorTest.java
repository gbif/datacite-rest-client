package org.gbif.datacite.jsonapi.annotator;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests {@link JsonApiAnnotator} class.
 * Should check all the jsonapi-converter annotations were added.
 */
@RunWith(MockitoJUnitRunner.class)
public class JsonApiAnnotatorTest {

    @Mock
    JFieldVar fieldMock;
    @Mock
    JDefinedClass classMock;
    @Mock
    JAnnotationUse annotationUseMock;
    @Mock
    JsonNode propertyNodeMock;

    // Class with property 'doi' should be annotated with the annotation 'Type'
    @Test
    public void classWithPropertyDoiShouldBeAnnotatedWithAnnotationType() {
        // given
        JsonApiAnnotator jsonApiAnnotator = new JsonApiAnnotator();
        when(classMock.annotate(Type.class)).thenReturn(annotationUseMock);
        when(annotationUseMock.param("value", "dois")).thenReturn(annotationUseMock);

        // when
        jsonApiAnnotator.propertyField(fieldMock, classMock, "doi", propertyNodeMock);

        // then
        verify(classMock, times(1)).annotate(Type.class);
    }

    // Property 'id' should be annotated with the annotation 'Id'
    @Test
    public void propertyIdShouldBeAnnotatedWithAnnotationId() {
        // given
        JsonApiAnnotator jsonApiAnnotator = new JsonApiAnnotator();
        when(fieldMock.annotate(Id.class)).thenReturn(annotationUseMock);

        // when
        jsonApiAnnotator.propertyField(fieldMock, classMock, "id", propertyNodeMock);

        // then
        verify(fieldMock, times(1)).annotate(Id.class);
    }
}
