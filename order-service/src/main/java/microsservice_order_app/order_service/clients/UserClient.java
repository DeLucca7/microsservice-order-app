package microsservice_order_app.order_service.clients;

import microsservice_order_app.order_service.dtos.responses.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "user-service")
public interface UserClient  {

    @GetMapping(value = "v1/user/getUserById/{userId}",
            headers = {"Content-Type= application/json"},
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    Optional<UserResponseDto> getUserById(@PathVariable("userId") Long userId);
}
