package gas.project.app.services.loginServices.LoginComponents;

import java.time.Instant;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import gas.project.app.database.entitys.User;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Component
public class CreateJwt {
    private final JwtEncoder jwtEncoder;

    public String create(Instant instant, String scopes, User user){
        return jwtEncoder.encode(JwtEncoderParameters.from(
            JwtClaimsSet.builder()
                .issuedAt(instant)
                .claim("scope", scopes)
                .issuer("GasProject")
                .subject(user.getId().toString())
                .build()
            )
        ).getTokenValue();
    }
}
