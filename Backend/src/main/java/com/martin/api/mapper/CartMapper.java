package com.martin.api.mapper;

import com.martin.api.dto.response.CartItemResponse;
import com.martin.api.dto.response.CartResponse;
import com.martin.api.persistence.model.Cart;
import com.martin.api.persistence.model.CartItem;
import java.util.List;

public class CartMapper {

  public static CartResponse toCartDTO(Cart cart) {
    if (cart == null) {
      return null;
    }

    return new CartResponse(
        cart.getId(),
        toListCartItemDTO(cart.getItems()),
        cart.getCreatedAt(),
        cart.getUpdatedAt()
    );
  }

  public static CartItemResponse toCartItemDTO(CartItem cartItem) {
    if (cartItem == null) {
      return null;
    }

    return new CartItemResponse(
        cartItem.getId(),
        ProductMapper.toProductReponse(cartItem.getProduct()),
        cartItem.getQuantity(),
        cartItem.getPrice()
    );
  }

  public static List<CartItemResponse> toListCartItemDTO(List<CartItem> cartItems) {
    if (cartItems == null) {
      return null;
    }

    return cartItems.stream()
        .map(CartMapper::toCartItemDTO)
        .toList();
  }
}
