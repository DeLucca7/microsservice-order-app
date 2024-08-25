package microsservice_order_app.auth_service.controllers;

import microsservice_order_app.auth_service.services.AuthService;
import microsservice_order_app.auth_service.v1.model.dto.LoginRequestDto;
import microsservice_order_app.auth_service.v1.model.dto.TokenDto;
import microsservice_order_app.auth_service.v1.model.dto.UserRegisterDto;
import microsservice_order_app.auth_service.v1.model.dto.UserRegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterDto> register(@RequestBody UserRegisterRequestDto request) {
        return ResponseEntity.ok(authService.register(request));
    }
}