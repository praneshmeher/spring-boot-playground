package com.example.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class AsyncService {

    private static final Logger log = LoggerFactory.getLogger(AsyncService.class);

    @Async
    public void sendWelcomeEmail(String to) {
        log.info("email start: {}", to);
        sleep(1500);
        log.info("email done: {}", to);
    }

    @Async
    public Future<String> generateReportFuture(String name) {
        log.info("report(future) start: {}", name);
        sleep(1200);
        String result = "report=" + name + ", at=" + Instant.now();
        log.info("report(future) done: {}", name);
        return new AsyncResult<>(result);
    }

    @Async
    public CompletableFuture<String> generateReportCompletable(String name) {
        log.info("report(cf) start: {}", name);
        sleep(1200);
        String result = "report=" + name + ", at=" + Instant.now();
        log.info("report(cf) done: {}", name);
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<BigDecimal> fetchPrice(String sku) {
        log.info("price start: {}", sku);
        sleep(700);
        log.info("price done: {}", sku);
        return CompletableFuture.completedFuture(new BigDecimal("199.99"));
    }

    @Async
    public CompletableFuture<Integer> fetchInventory(String sku) {
        log.info("inventory start: {}", sku);
        sleep(900);
        log.info("inventory done: {}", sku);
        return CompletableFuture.completedFuture(12);
    }

    @Async
    public CompletableFuture<Integer> fetchShippingEtaDays(String sku) {
        log.info("eta start: {}", sku);
        sleep(800);
        log.info("eta done: {}", sku);
        return CompletableFuture.completedFuture(3);
    }

    public QuoteData fetchQuoteSequential(String sku) {
        BigDecimal price = blockingFetchPrice(sku);
        int inventory = blockingFetchInventory(sku);
        int etaDays = blockingFetchShippingEtaDays(sku);
        return new QuoteData(sku, price, inventory, etaDays);
    }

    private BigDecimal blockingFetchPrice(String sku) {
        log.info("(seq) price start: {}", sku);
        sleep(700);
        log.info("(seq) price done: {}", sku);
        return new BigDecimal("199.99");
    }

    private int blockingFetchInventory(String sku) {
        log.info("(seq) inventory start: {}", sku);
        sleep(900);
        log.info("(seq) inventory done: {}", sku);
        return 12;
    }

    private int blockingFetchShippingEtaDays(String sku) {
        log.info("(seq) eta start: {}", sku);
        sleep(800);
        log.info("(seq) eta done: {}", sku);
        return 3;
    }

    public record QuoteData(String sku, BigDecimal price, int inventory, int etaDays) {
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
