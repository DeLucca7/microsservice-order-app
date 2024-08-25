package microsservice_order_app.user_service.controllers.v1.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import microsservice_order_app.user_service.v1.model.UserDetails;

@Data
public class UserUpdateRequest {
    @NotBlank(message = "Informe o id")
    private Long userId;
    private String username;
    private String password;
    private UserDetails userDetails;
}
