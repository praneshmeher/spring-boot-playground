package com.example.cache;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ProductCacheController {

    private final ProductCacheService service;

    public ProductCacheController(ProductCacheService service) {
        this.service = service;
    }

    @GetMapping("/cache/products/{id}")
    public Product get(@PathVariable long id) {
        return service.getProduct(id);
    }

    @PutMapping("/cache/products/{id}")
    public Product update(@PathVariable long id, @RequestParam BigDecimal price) {
        return service.updatePrice(id, price);
    }

    @DeleteMapping("/cache/products/{id}")
    public String evict(@PathVariable long id) {
        service.evictProduct(id);
        return "evicted";
    }
}

