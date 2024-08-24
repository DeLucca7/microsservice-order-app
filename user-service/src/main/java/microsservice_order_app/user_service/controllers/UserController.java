package microsservice_order_app.user_service.controllers;

import jakarta.validation.Valid;
import microsservice_order_app.user_service.controllers.v1.request.UserRegisterRequest;
import microsservice_order_app.user_service.controllers.v1.request.UserUpdateRequest;
import microsservice_order_app.user_service.services.UserService;
import microsservice_order_app.user_service.services.exceptions.UserException;
import microsservice_order_app.user_service.v1.model.dtos.AuthUserDto;
import microsservice_order_app.user_service.v1.model.dtos.UserDto;
import microsservice_order_app.user_service.v1.model.enums.Authorities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(modelMapper.map(userService.createUser(request, Authorities.USER), UserDto.class));
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(modelMapper.map(userService.getUserById(userId), UserDto.class));
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(modelMapper.map(userService.getUserByEmail(email.trim()), UserDto.class));
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<AuthUserDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(modelMapper.map(userService.getUserByUsername(username), AuthUserDto.class));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class)).toList());
    }

    @GetMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> update(@Valid @RequestBody UserUpdateRequest request) {
        try{
            userService.updateUser(request);
            return ResponseEntity.ok("Usuário atualizado");
        } catch (UserException ex){
            return ResponseEntity.status(ex.getHttpStatusCode()).body(ex.getMessage());
        }
    }

    @DeleteMapping("/deleteUserById/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }
}