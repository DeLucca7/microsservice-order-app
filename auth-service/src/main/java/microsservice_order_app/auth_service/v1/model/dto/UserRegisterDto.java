package microsservice_order_app.auth_service.v1.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRegisterDto {
    private String userId;
    private String username;
    private String email;
}
