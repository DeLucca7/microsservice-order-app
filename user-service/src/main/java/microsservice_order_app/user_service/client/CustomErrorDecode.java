package microsservice_order_app.user_service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import microsservice_order_app.user_service.client.exceptions.DefaultResponseException;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CustomErrorDecode implements ErrorDecoder {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        try (InputStream body = response.body().asInputStream()) {
            Map<String, String> errors = mapper.readValue(IOUtils.toString(body, StandardCharsets.UTF_8), Map.class);
            return DefaultResponseException
                    .builder()
                    .httpStatus(HttpStatus.valueOf(response.status()))
                    .message(errors.get("Erro"))
                    .build();
        } catch (IOException exception) {
            throw DefaultResponseException.builder()
                    .httpStatus(HttpStatus.valueOf(response.status()))
                    .message(exception.getMessage())
                    .build();
        }
    }
}