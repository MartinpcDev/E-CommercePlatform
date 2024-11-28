package com.martin.api.dto.request;

import jakarta.validation.constraints.NotNull;

public record CartRequest(
    @NotNull(message = "el user_id no deber ir vacio")
    Long userId
) {

}
