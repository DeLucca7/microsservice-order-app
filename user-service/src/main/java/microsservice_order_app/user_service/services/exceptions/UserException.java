package microsservice_order_app.user_service.services.exceptions;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private int httpStatusCode;

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, int httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }
}
