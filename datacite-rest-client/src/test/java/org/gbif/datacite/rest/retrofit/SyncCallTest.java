package org.gbif.datacite.rest.retrofit;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import org.gbif.datacite.model.json.Datacite42Schema;
import org.gbif.datacite.rest.client.configuration.SyncCall;
import org.gbif.datacite.rest.client.exception.DataCiteClientException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests {@link SyncCall#syncCallWithResponse(Call)} method.
 */
@ExtendWith(MockitoExtension.class)
class SyncCallTest {

    @Mock
    Call<JSONAPIDocument<Datacite42Schema>> callMock;
    @Mock
    Response<JSONAPIDocument<Datacite42Schema>> responseMock;

    @Test
    @DisplayName("Should return response")
    void shouldReturnResponse() throws IOException {
        // given
        when(callMock.execute()).thenReturn(responseMock);

        // when
        Response<JSONAPIDocument<Datacite42Schema>> actual = SyncCall.syncCallWithResponse(callMock);

        // then
        assertNotNull(actual);
        verify(callMock, times(1)).execute();
    }

    @Test
    @DisplayName("Should throw DataCiteClientException when method 'execute' throws IOException")
    void shouldThrowDataCiteClientExceptionWhenMethodExecuteThrowsIOException() throws IOException {
        // given
        when(callMock.execute()).thenThrow(IOException.class);

        // when & then
        assertThrows(DataCiteClientException.class, () -> SyncCall.syncCallWithResponse(callMock));
    }
}
