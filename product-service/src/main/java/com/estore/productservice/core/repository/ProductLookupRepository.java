package com.estore.productservice.core.repository;

import com.estore.productservice.core.entity.ProductLookup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupRepository extends JpaRepository<ProductLookup, String> {
    ProductLookup findByIdOrTitle(final String id, final String title);
}
