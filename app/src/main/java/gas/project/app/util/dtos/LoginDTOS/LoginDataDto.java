package gas.project.app.util.dtos.LoginDTOS;

import jakarta.validation.constraints.NotEmpty;

public record LoginDataDto(@NotEmpty String username, @NotEmpty String password) {}