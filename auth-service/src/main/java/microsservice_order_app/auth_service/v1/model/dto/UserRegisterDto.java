package microsservice_order_app.auth_service.v1.model.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private Long userId;
    private String username;
    private String email;
}