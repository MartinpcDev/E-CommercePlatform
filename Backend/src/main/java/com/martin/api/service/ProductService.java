package com.martin.api.service;

import com.martin.api.dto.request.ProductRequest;
import com.martin.api.dto.request.UpdateProductRequest;
import com.martin.api.dto.response.DeleteResponse;
import com.martin.api.dto.response.ProductResponse;
import java.util.List;

public interface ProductService {

  List<ProductResponse> allProducts();

  ProductResponse getProductById(Long productId);

  ProductResponse createProduct(ProductRequest request);

  ProductResponse updateProduct(Long productId, UpdateProductRequest request);

  DeleteResponse deleteProduct(Long productId);
}
