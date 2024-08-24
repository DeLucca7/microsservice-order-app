package microsservice_order_app.auth_service.v1.model.dto;

import lombok.Getter;

@Getter
public class UserRegisterRequestDto {
    private String username;
    private String password;
    private String email;
}