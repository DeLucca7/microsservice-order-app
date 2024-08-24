package microsservice_order_app.auth_service.v1.model.dto;

import lombok.Data;
import microsservice_order_app.auth_service.v1.model.enums.Role;

@Data
public class UserDto {
    private Long userId;
    private String username;
    private String password;
    private Role role;
}
