package com.martin.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UpdateProductRequest(
    @Size(min = 4, max = 60, message = "El name debe tener de 4 a 60 caracteres")
    String name,
    @Size(min = 15, max = 200, message = "El name debe tener de 15 a 200 caracteres")
    String description,
    @Min(value = 10, message = "El minimo valor del price es de 10.0")
    @Max(value = 999, message = "el maximo valor del price es de 999.0")
    Double price,
    @Min(value = 20, message = "el minimo valor del stock es de 20")
    @Max(value = 999, message = "el maximo valor del stock es de 999")
    Integer stock
) {

}
