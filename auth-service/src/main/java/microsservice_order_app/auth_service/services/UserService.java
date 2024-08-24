package microsservice_order_app.auth_service.services;

import microsservice_order_app.auth_service.dataproviders.feign.UserServiceFeign;
import microsservice_order_app.auth_service.v1.model.dto.UserDto;
import microsservice_order_app.auth_service.v1.model.utils.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserServiceFeign userServiceFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userServiceFeign.getUserByUsername(username).getBody();
        assert userDto != null;
        return new CustomUserDetails(userDto);
    }
}
