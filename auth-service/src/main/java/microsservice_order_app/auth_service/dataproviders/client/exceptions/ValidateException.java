package microsservice_order_app.auth_service.dataproviders.client.exceptions;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class ValidateException extends RuntimeException {
    private Map<String, String> validateError;
}
