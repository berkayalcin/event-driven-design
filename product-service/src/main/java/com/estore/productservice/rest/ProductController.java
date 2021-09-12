package com.estore.productservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private Environment environment;

    @PostMapping
    public String create() {
        return "Http POST Handled";
    }

    @GetMapping
    public String get() {
        return "Http GET Handled By " + environment.getProperty("local.server.port");
    }

    @PutMapping
    public String update() {
        return "Http PUT Handled";
    }

    @DeleteMapping
    public String delete() {
        return "Http DELETE Handled";
    }
}
