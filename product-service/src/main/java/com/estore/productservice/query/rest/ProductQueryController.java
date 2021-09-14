package com.estore.productservice.query.rest;

import com.estore.productservice.query.model.FindProductsQuery;
import com.estore.productservice.query.model.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductQueryController {
    private final QueryGateway queryGateway;

    @GetMapping
    public List<ProductDTO> getProducts() {
        final var findProductsQuery = new FindProductsQuery();
        return queryGateway.query(findProductsQuery, ResponseTypes.multipleInstancesOf(ProductDTO.class)).join();
    }
}
