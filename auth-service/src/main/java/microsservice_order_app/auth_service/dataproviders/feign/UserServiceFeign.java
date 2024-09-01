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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "user-service")
public interface UserServiceFeign {

    @PostMapping(value = "/v1/user/create",
            headers = {"Content-Type= application/json"},
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<UserRegisterDto> create(@RequestBody UserRegisterRequestDto request);

    @GetMapping(value = "/v1/user/getUserByUsername/{username}",
            headers = {"Content-Type= application/json"},
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username);
}
