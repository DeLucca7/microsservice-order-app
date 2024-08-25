package microsservice_order_app.auth_service.dataproviders.client.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GenericResponseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public GenericResponseException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}