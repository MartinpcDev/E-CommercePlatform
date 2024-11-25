package com.martin.api.mapper;

import com.martin.api.dto.request.ProductRequest;
import com.martin.api.dto.request.UpdateProductRequest;
import com.martin.api.dto.response.ProductResponse;
import com.martin.api.persistence.model.Product;
import java.util.List;

public class ProductMapper {

  public static ProductResponse toProductReponse(Product product) {
    if (product == null) {
      return null;
    }

    return new ProductResponse(
        product.getId(),
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        product.getStock()
    );
  }

  public static List<ProductResponse> toListProductResponse(List<Product> products) {
    if (products == null) {
      return null;
    }

    return products.stream()
        .map(ProductMapper::toProductReponse)
        .toList();
  }

  public static Product toProduct(ProductRequest request) {
    if (request == null) {
      return null;
    }

    Product product = new Product();
    product.setName(request.name());
    product.setDescription(request.description());
    product.setPrice(request.price());
    product.setStock(request.stock());

    return product;
  }

  public static void updateProduct(Product oldProduct, UpdateProductRequest request) {
    if (request != null) {
      if (request.name() != null) {
        oldProduct.setName(request.name());
      }
      if (request.description() != null) {
        oldProduct.setDescription(request.description());
      }
      if (request.price() != null) {
        oldProduct.setPrice(request.price());
      }
      if (request.stock() != null) {
        oldProduct.setStock(request.stock());
      }
    }
  }
}
