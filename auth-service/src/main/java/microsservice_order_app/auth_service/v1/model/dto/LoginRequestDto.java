package microsservice_order_app.auth_service.v1.model.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    public String username;
    public String password;
}