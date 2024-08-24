package microsservice_order_app.user_service.v1.model.utils;

import microsservice_order_app.user_service.controllers.v1.request.UserRegisterRequest;
import microsservice_order_app.user_service.services.UserService;
import microsservice_order_app.user_service.v1.model.enums.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderUtil implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setUsername("admin");
        request.setPassword("Senha123%");
        request.setEmail("admin@admin.com");
        userService.createUser(request, Authorities.ADMIN);
    }
}
