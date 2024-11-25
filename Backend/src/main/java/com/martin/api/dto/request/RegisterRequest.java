package com.martin.api.dto.request;

public record RegisterRequest(
    String username,
    String email,
    String password
) {

}
