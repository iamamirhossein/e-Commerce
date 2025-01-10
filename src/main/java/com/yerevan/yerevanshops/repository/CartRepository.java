package com.yerevan.yerevanshops.repository;

import com.yerevan.yerevanshops.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
