package microsservice_order_app.auth_service.dataproviders.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import microsservice_order_app.auth_service.dataproviders.client.exceptions.GenericResponseException;
import microsservice_order_app.auth_service.dataproviders.client.exceptions.ValidateException;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        try(InputStream responseBody = response.body().asInputStream()) {
            Map<String, String> error = mapper.readValue(IOUtils.toString(responseBody, StandardCharsets.UTF_8), Map.class);
            if(response.status() == 400) {
                return ValidateException.builder().validateError(error).build();
            } else {
                return new GenericResponseException(error.get("error"), HttpStatus.valueOf(response.status()));
            }
        } catch (IOException ex) {
            throw new GenericResponseException(ex.getMessage(), HttpStatus.valueOf(response.status()));
        }
    }
}