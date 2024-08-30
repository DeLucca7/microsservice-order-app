package microsservice_order_app.order_service.services;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import microsservice_order_app.order_service.clients.UserClient;
import microsservice_order_app.order_service.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserClient userClient;

    @SneakyThrows
    public void checkIfUserExists(Long idUser) {
        try {
            if (!this.userClient.getUserById(idUser).isPresent()) throw new UserNotFoundException("Usuário não encontrado.");
        } catch (FeignException.NotFound exception) {
            throw new UserNotFoundException("Usuário não encontrado.");
        }
    }
}
