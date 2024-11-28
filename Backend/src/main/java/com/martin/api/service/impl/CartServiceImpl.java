package com.martin.api.service.impl;

import com.martin.api.dto.request.CartItemRequest;
import com.martin.api.dto.response.CartResponse;
import com.martin.api.exception.CartNotFoundException;
import com.martin.api.exception.InsufficientStockException;
import com.martin.api.exception.ProductNotFoundException;
import com.martin.api.exception.UserNotFoundException;
import com.martin.api.mapper.CartMapper;
import com.martin.api.persistence.model.Cart;
import com.martin.api.persistence.model.CartItem;
import com.martin.api.persistence.model.Product;
import com.martin.api.persistence.model.User;
import com.martin.api.persistence.repository.CartRepository;
import com.martin.api.persistence.repository.ProductRepository;
import com.martin.api.persistence.repository.UserRepository;
import com.martin.api.service.CartService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  @Override
  public CartResponse addToCart(Long userId, CartItemRequest request) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("Uset not found"));
    Product product = productRepository.findById(request.productId())
        .orElseThrow(() -> new ProductNotFoundException("Product not found"));

    if (product.getStock() < request.quantity()) {
      throw new InsufficientStockException("Not enough available");
    }

    Cart cart = cartRepository.findByUserId(userId)
        .orElse(new Cart(null, user, new ArrayList<>(), LocalDateTime.now(), LocalDateTime.now()));

    Optional<CartItem> existingCartItem = cart.getItems().stream()
        .filter(i -> i.getProduct().getId().equals(request.productId()))
        .findFirst();

    if (existingCartItem.isPresent()) {
      CartItem cartItem = existingCartItem.get();
      cartItem.setQuantity(cartItem.getQuantity() + request.quantity());
    } else {
      CartItem cartItem = new CartItem(null, cart, product, request.quantity(),
          request.quantity() * product.getPrice());
      cart.getItems().add(cartItem);
    }

    Cart savedCart = cartRepository.save(cart);

    return CartMapper.toCartDTO(savedCart);
  }

  @Override
  public CartResponse getCart(Long userId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new CartNotFoundException("Cart not found"));
    return CartMapper.toCartDTO(cart);
  }

  @Override
  public void clearCart(Long userId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new CartNotFoundException("Cart not found"));
    cart.getItems().clear();
    cartRepository.save(cart);
  }
}
