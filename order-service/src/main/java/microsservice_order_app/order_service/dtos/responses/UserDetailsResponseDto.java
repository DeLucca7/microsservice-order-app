package microsservice_order_app.order_service.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailsResponseDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String country;
    private String city;
    private String address;
    private String cep;
}
