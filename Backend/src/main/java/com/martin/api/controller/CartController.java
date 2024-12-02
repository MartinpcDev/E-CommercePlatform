package com.martin.api.controller;

import com.martin.api.dto.request.CartItemRequest;
import com.martin.api.dto.response.CartResponse;
import com.martin.api.service.CartService;
import com.martin.api.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @PostMapping("/add")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<CartResponse> addToCart(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestBody CartItemRequest request
  ) {
    Long userId = UserUtils.extractId(userDetails);
    return ResponseEntity.ok(cartService.addToCart(userId, request));
  }

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<CartResponse> findCart(
      @AuthenticationPrincipal UserDetails userDetails
  ) {
    Long userId = UserUtils.extractId(userDetails);
    return ResponseEntity.ok(cartService.getCart(userId));
  }

  @PostMapping("/clear")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<CartResponse> clearCart(
      @AuthenticationPrincipal UserDetails userDetails
  ) {
    Long userId = UserUtils.extractId(userDetails);
    cartService.clearCart(userId);
    return ResponseEntity.noContent().build();
  }
}
