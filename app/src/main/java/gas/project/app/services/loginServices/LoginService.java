package gas.project.app.services.loginServices;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Service;

import gas.project.app.database.entitys.Role;
import gas.project.app.database.entitys.User;
import gas.project.app.database.repositories.UserRepo;
import gas.project.app.services.loginServices.LoginComponents.CreateJwt;
import gas.project.app.util.dtos.LoginDTOS.LoginDataDto;
import gas.project.app.util.dtos.LoginDTOS.ResponseLoginDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LoginService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CreateJwt createJwt;

    public ResponseEntity<ResponseLoginDto> login(LoginDataDto loginDataDto) throws BadRequestException{
        Optional<User> user = userRepo .findByusername(loginDataDto.username());

        if(user.isEmpty() || !passwordEncoder.matches(loginDataDto.password(), user.get().getPassword())){
            throw new BadRequestException("The passwrod or username is incorrect");
        }
        
        Instant now = Instant.now();
        long expiration = 300L;
        String scopes = user.get().getRoles().stream().map(Role::getName).collect(Collectors.joining(" ")); 

        var jwt = createJwt.create(now, scopes, user.get());

        return ResponseEntity.ok(new ResponseLoginDto(jwt, expiration));
    }
}
