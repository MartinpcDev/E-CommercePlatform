package com.martin.api.service;

import com.martin.api.dto.request.CartItemRequest;
import com.martin.api.dto.response.CartResponse;

public interface CartService {

  CartResponse addToCart(Long userId, CartItemRequest request);

  CartResponse getCart(Long userId);

  void clearCart(Long userId);
}
