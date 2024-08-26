package microsservice_order_app.order_service.v1.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long userId;
    private String username;
    private String email;
    private UserDetailsDto userDetails;
}
