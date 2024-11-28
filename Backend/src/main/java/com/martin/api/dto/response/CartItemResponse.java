package com.martin.api.dto.response;

public record CartItemResponse(
    Long id,
    ProductResponse product,
    Integer quantity,
    Double price
) {

}
