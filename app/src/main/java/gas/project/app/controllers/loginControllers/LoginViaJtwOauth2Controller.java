package gas.project.app.controllers.loginControllers;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gas.project.app.services.loginServices.LoginService;
import gas.project.app.util.dtos.LoginDTOS.LoginDataDto;
import gas.project.app.util.dtos.LoginDTOS.ResponseLoginDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController("/auth")
public class LoginViaJtwOauth2Controller {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDto> login_based_in_jwtToken(@RequestBody @Valid LoginDataDto loginDataDto) throws BadRequestException{
        return loginService.login(loginDataDto);
    }


}
