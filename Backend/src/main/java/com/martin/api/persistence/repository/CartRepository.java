package com.martin.api.persistence.repository;

import com.martin.api.persistence.model.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

  Optional<Cart> findByUserId(Long userId);
}
