package com.martin.api.service.impl;

import com.martin.api.dto.request.ProductRequest;
import com.martin.api.dto.request.UpdateProductRequest;
import com.martin.api.dto.response.DeleteResponse;
import com.martin.api.dto.response.ProductResponse;
import com.martin.api.exception.ProductDuplicatedException;
import com.martin.api.exception.ProductNotFoundException;
import com.martin.api.mapper.ProductMapper;
import com.martin.api.persistence.model.Product;
import com.martin.api.persistence.repository.ProductRepository;
import com.martin.api.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public List<ProductResponse> allProducts() {
    List<Product> products = productRepository.findAll().stream()
        .filter(p -> p.getStock() > 0)
        .toList();

    return ProductMapper.toListProductResponse(products);
  }

  @Override
  public ProductResponse getProductById(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException("El producto con el id " + productId +
            " no existe"));
    return ProductMapper.toProductReponse(product);
  }

  @Override
  public ProductResponse createProduct(ProductRequest request) {
    if (productRepository.existsByNameIgnoreCase(request.name())) {
      throw new ProductDuplicatedException(
          "El product con el name " + request.name() + " ya existe");
    }

    Product product = ProductMapper.toProduct(request);
    Product createdProduct = productRepository.save(product);
    return ProductMapper.toProductReponse(createdProduct);
  }

  @Override
  public ProductResponse updateProduct(Long productId, UpdateProductRequest request) {
    if (productRepository.existsByNameIgnoreCase(request.name())) {
      throw new ProductDuplicatedException(
          "El product con el name " + request.name() + " ya existe");
    }

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException("El producto no existe"));
    ProductMapper.updateProduct(product, request);
    Product updatedProduct = productRepository.save(product);
    return ProductMapper.toProductReponse(updatedProduct);
  }

  @Override
  public DeleteResponse deleteProduct(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException("El producto no existe"));
    productRepository.delete(product);
    return new DeleteResponse("Producto eliminado Satisfactoriamente");
  }
}
