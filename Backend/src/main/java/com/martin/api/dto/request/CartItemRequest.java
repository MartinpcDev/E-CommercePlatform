package com.martin.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CartItemRequest(
    @NotNull(message = "el product_id no deber ir vacio")
    Long productId,
    @NotNull(message = "la quantity no deber ir vacio")
    @Positive(message = "la quantity no puede ser negativo")
    Integer quantity
) {

}
