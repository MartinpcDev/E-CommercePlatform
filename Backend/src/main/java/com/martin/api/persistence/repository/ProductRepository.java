package com.martin.api.persistence.repository;

import com.martin.api.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  boolean existsByNameIgnoreCase(String name);
}
