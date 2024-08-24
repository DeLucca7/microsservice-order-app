package microsservice_order_app.auth_service.dataproviders.feign;

import microsservice_order_app.auth_service.v1.model.dto.UserDto;
import microsservice_order_app.auth_service.v1.model.dto.UserRegisterDto;
import microsservice_order_app.auth_service.v1.model.dto.UserRegisterRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${api.url.user-service}")
public interface UserServiceFeign {

    @PostMapping("/create")
    ResponseEntity<UserRegisterDto> create(@RequestBody UserRegisterRequestDto request);

    @GetMapping("/getUserByUsername/{username}")
    ResponseEntity<UserDto> getUserByUsername(@PathVariable String username);
}
