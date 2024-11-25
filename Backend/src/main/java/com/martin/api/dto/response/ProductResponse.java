package com.martin.api.dto.response;

public record ProductResponse(
    Long id,
    String name,
    String description,
    Double price,
    Integer stock
) {

}
