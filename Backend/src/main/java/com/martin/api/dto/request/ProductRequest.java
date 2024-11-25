package com.martin.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequest(
    @NotBlank(message = "El name no puede ir vacio")
    @Size(min = 4, max = 60, message = "El name debe tener de 4 a 60 caracteres")
    String name,
    @NotBlank(message = "El name no puede ir vacio")
    @Size(min = 15, max = 200, message = "El name debe tener de 15 a 200 caracteres")
    String description,
    @NotNull(message = "El price no puede estar vacio")
    @Min(value = 10, message = "El minimo valor del price es de 10.0")
    @Max(value = 999, message = "el maximo valor del price es de 999.0")
    Double price,
    @NotNull(message = "El stock no puede estar vacio")
    @Min(value = 20, message = "el minimo valor del stock es de 20")
    @Max(value = 999, message = "el maximo valor del stock es de 999")
    Integer stock
) {

}
