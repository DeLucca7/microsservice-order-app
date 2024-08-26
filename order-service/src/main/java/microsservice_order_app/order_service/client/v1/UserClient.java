package microsservice_order_app.order_service.client.v1;

import microsservice_order_app.order_service.v1.model.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "http://localhost:8081/user")
public interface UserClient  {

    @GetMapping("/getUserById/{userId}")
    UserDto getUserById(@PathVariable Long userId);
}
