package org.gbif.datacite.rest.retrofit;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import org.gbif.datacite.model.json.Datacite42Schema;
import org.gbif.datacite.rest.client.configuration.SyncCall;
import org.gbif.datacite.rest.client.exception.DataCiteClientException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests {@link SyncCall#syncCallWithResponse(Call)} method.
 */
    @RunWith(MockitoJUnitRunner.class)
public class SyncCallTest {

    @Mock
    Call<JSONAPIDocument<Datacite42Schema>> callMock;
    @Mock
    Response<JSONAPIDocument<Datacite42Schema>> responseMock;

    @Test
    public void shouldReturnResponse() throws IOException {
        // given
        when(callMock.execute()).thenReturn(responseMock);

        // when
        Response<JSONAPIDocument<Datacite42Schema>> actual = SyncCall.syncCallWithResponse(callMock);

        // then
        assertNotNull(actual);
        verify(callMock, times(1)).execute();
    }

    // Should throw DataCiteClientException when method 'execute' throws IOException
    @Test(expected = DataCiteClientException.class)
    public void shouldThrowDataCiteClientExceptionWhenMethodExecuteThrowsIOException() throws IOException {
        // given
        when(callMock.execute()).thenThrow(IOException.class);

        // when
        SyncCall.syncCallWithResponse(callMock);

        // then
        verify(callMock).execute();
    }
}
