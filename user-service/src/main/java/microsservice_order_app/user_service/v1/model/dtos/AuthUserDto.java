package microsservice_order_app.user_service.v1.model.dtos;

import lombok.Data;
import microsservice_order_app.user_service.v1.model.enums.*;

@Data
public class AuthUserDto {
    private Long userId;
    private String username;
    private String password;
    private Authorities role;
}
