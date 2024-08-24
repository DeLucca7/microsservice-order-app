package microsservice_order_app.auth_service.services.exceptions;

public class AuthenticateException extends RuntimeException {

    private int httpStatusCode;

    public AuthenticateException(String message){
        super(message);
    }

    public AuthenticateException(String message, int httpStatusCode){
        super(message);
        this.httpStatusCode = httpStatusCode;
    }
}
