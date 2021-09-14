package com.estore.productservice.core.repository;

import com.estore.productservice.core.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByIdOrTitle(final String id, final String title);
}
