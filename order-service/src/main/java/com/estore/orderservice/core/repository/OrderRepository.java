package com.estore.orderservice.core.repository;

import com.estore.orderservice.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
