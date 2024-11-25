package com.martin.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @NotBlank(message = "El username no puede ir vacio")
    @Size(min = 4, max = 60, message = "El username debe estar entre 4 a 60 caracteres")
    String username,
    @NotBlank(message = "El password no puede ir vacio")
    @Size(min = 8, max = 60, message = "El password debe estar entre 4 a 60 caracteres")
    String password
) {

}
