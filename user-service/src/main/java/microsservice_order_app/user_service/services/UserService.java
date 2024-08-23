package microsservice_order_app.user_service.services;

import lombok.RequiredArgsConstructor;
import microsservice_order_app.user_service.controllers.v1.request.UserRegisterRequest;
import microsservice_order_app.user_service.controllers.v1.request.UserUpdateRequest;
import microsservice_order_app.user_service.repositories.RoleRepository;
import microsservice_order_app.user_service.repositories.UserRepository;
import microsservice_order_app.user_service.services.exceptions.UserException;
import microsservice_order_app.user_service.v1.model.Role;
import microsservice_order_app.user_service.v1.model.User;
import microsservice_order_app.user_service.v1.model.enums.Active;
import microsservice_order_app.user_service.v1.model.enums.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserRegisterRequest request, Authorities role) throws UserException {
        Role roleName = roleRepository.findByName(role.name());
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(roleName))
                .active(Active.ACTIVE)
                .build();
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAllByActive(Active.ACTIVE);
    }

    public void updateUser(UserUpdateRequest request){
        User userToUpdate = getUserById(request.getUserId());
        userRepository.updateUserDetails(userToUpdate.getUserId(), request.getUserDetails());
    }

    public void deleteUserById(Long userId){
        User userToDelete = getUserById(userId);
        userRepository.updateUserActiveStatus(userToDelete.getUserId(), Active.INACTIVE);
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("Usuário não encontrado", HttpStatus.NOT_FOUND.value()));
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("Usuário não encontrado", HttpStatus.NOT_FOUND.value()));
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("Usuário não encontrado", HttpStatus.NOT_FOUND.value()));
    }
}
