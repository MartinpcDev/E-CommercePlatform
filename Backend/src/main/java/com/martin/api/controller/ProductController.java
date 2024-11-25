package com.martin.api.controller;

import com.martin.api.dto.request.ProductRequest;
import com.martin.api.dto.request.UpdateProductRequest;
import com.martin.api.dto.response.DeleteResponse;
import com.martin.api.dto.response.ProductResponse;
import com.martin.api.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getProducts() {
    return ResponseEntity.ok(productService.allProducts());
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> productById(@PathVariable Long productId) {
    return ResponseEntity.ok(productService.getProductById(productId));
  }

  @PostMapping
  @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
  public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(productService.createProduct(request));
  }

  @PutMapping("/{productId}")
  @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
  public ResponseEntity<ProductResponse> updateProduct(
      @PathVariable Long productId,
      @RequestBody @Valid UpdateProductRequest request) {
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(productService.updateProduct(productId, request));
  }

  @DeleteMapping("/{productId}")
  @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
  public ResponseEntity<DeleteResponse> deleteProduct(@PathVariable Long productId) {
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(productService.deleteProduct(productId));
  }
}
