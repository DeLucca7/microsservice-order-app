package microsservice_order_app.order_service.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private Long userId;
    private String username;
    private String email;
    private UserDetailsResponseDto userDetails;
}
