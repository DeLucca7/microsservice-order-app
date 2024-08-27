package microsservice_order_app.order_service.clients;

import microsservice_order_app.order_service.dtos.responses.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "http://localhost:8081/user")
public interface UserClient  {

    @GetMapping("/getUserById/{userId}")
    UserResponseDto getUserById(@PathVariable Long userId);
}
