package microsservice_order_app.auth_service.v1.model.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserRegisterRequestDto {
    private String username;
    private String password;
    private String email;
}