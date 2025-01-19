package gas.project.app.services.priceServices.priceComponents.createComponentModules;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import gas.project.app.database.entitys.User;
import gas.project.app.database.repositories.UserRepo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class FetchUser {
    private final UserRepo userRepo;

    public User fetchUser(JwtAuthenticationToken token){
        

        return new User();
    }
}
