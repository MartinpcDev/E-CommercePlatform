package com.martin.api.persistence.repository;

import com.martin.api.persistence.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
