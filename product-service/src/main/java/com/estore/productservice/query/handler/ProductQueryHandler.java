package com.estore.productservice.query.handler;

import com.estore.productservice.core.repository.ProductRepository;
import com.estore.productservice.query.model.FindProductsQuery;
import com.estore.productservice.query.model.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {
    private final ProductRepository productRepository;

    @QueryHandler
    public List<ProductDTO> findProducts(final FindProductsQuery findProductsQuery) {
        final var products = productRepository.findAll();
        return products.stream()
                .map(product -> ProductDTO.builder()
                        .id(product.getId())
                        .quantity(product.getQuantity())
                        .title(product.getTitle())
                        .price(product.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
