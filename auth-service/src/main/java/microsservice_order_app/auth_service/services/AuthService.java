package microsservice_order_app.auth_service.services;

import microsservice_order_app.auth_service.dataproviders.feign.UserServiceFeign;
import microsservice_order_app.auth_service.services.exceptions.AuthenticateException;
import microsservice_order_app.auth_service.v1.model.dto.LoginRequestDto;
import microsservice_order_app.auth_service.v1.model.dto.TokenDto;
import microsservice_order_app.auth_service.v1.model.dto.UserRegisterDto;
import microsservice_order_app.auth_service.v1.model.dto.UserRegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceFeign userServiceFeign;

    @Autowired
    private TokenService tokenService;

    public TokenDto login(LoginRequestDto loginRequest) {
        Authentication authUser = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if(authUser.isAuthenticated())
           return TokenDto.builder()
                   .token(tokenService.getToken(loginRequest.getUsername()))
                   .build();
        else throw new AuthenticateException("Invalid credentials", HttpStatus.UNAUTHORIZED.value());
    }

    public UserRegisterDto register(UserRegisterRequestDto registerRequest) {
        return userServiceFeign.create(registerRequest).getBody();
    }
}