package microsservice_order_app.user_service.controllers.v1.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @NotBlank(message = "Informe um usuário")
    @Size(min = 5, message = "Usuário deve ter no mínimo 6 caracteres")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Senha deve ter no mínimo 8 caracteres e conter no mínimo uma letra e um número")
    @NotNull(message = "Informe uma senha")
    private String password;
    @Email(message = "Informe um e-mail válido")
    private String email;
}
