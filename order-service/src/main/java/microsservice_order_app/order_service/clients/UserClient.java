package microsservice_order_app.order_service.clients;

import microsservice_order_app.order_service.dtos.responses.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(url = "http://localhost:8081/user", name = "user-service")
public interface UserClient  {

    @GetMapping("/getUserById/{userId}")
    Optional<UserResponseDto> getUserById(@PathVariable("userId") Long userId);
}
