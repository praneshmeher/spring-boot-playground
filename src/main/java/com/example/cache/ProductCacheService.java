package com.example.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductCacheService {

    private static final Logger log = LoggerFactory.getLogger(ProductCacheService.class);

    private final Map<Long, Product> db = new ConcurrentHashMap<>();

    public ProductCacheService() {
        db.put(1L, new Product(1L, "Pen", new BigDecimal("10.00")));
        db.put(2L, new Product(2L, "Book", new BigDecimal("50.00")));
    }

    @Cacheable(cacheNames = "products", keyGenerator = "simpleKeyGen")
    public Product getProduct(long id) {
        log.info("db hit: getProduct {}", id);
        sleep(700);
        return db.get(id);
    }

    @CachePut(cacheNames = "products", keyGenerator = "simpleKeyGen")
    public Product updatePrice(long id, BigDecimal price) {
        log.info("db hit: updatePrice {}", id);
        Product old = db.get(id);
        Product updated = new Product(id, old == null ? ("P" + id) : old.name(), price);
        db.put(id, updated);
        return updated;
    }

    @CacheEvict(cacheNames = "products", keyGenerator = "simpleKeyGen")
    public void evictProduct(long id) {
        log.info("cache evict called: {}", id);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

