package microsservice_order_app.user_service.client.exceptions;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class DefaultResponseException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public DefaultResponseException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}