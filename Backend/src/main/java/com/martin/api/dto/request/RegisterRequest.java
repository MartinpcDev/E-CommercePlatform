package com.martin.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "El username no puede ir vacio")
    @Size(min = 4, max = 60, message = "El username debe estar entre 4 a 60 caracteres")
    String username,
    @NotBlank(message = "El email no puede ir vacio")
    @Email(message = "El email tiene un formato invalido")
    String email,
    @NotBlank(message = "El password no puede ir vacio")
    @Size(min = 8, max = 60, message = "El password debe estar entre 4 a 60 caracteres")
    String password
) {

}
