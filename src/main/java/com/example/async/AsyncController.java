package com.example.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@RestController
public class AsyncController {

    private static final Logger log = LoggerFactory.getLogger(AsyncController.class);

    private final AsyncService asyncService;

    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @PostMapping("/async/email")
    public String sendEmail(@RequestParam(defaultValue = "alice@example.com") String to) {
        asyncService.sendWelcomeEmail(to);
        return "queued";
    }

    @GetMapping("/async/report/future")
    public Future<String> reportFuture(@RequestParam(defaultValue = "sales") String name) {
        return asyncService.generateReportFuture(name);
    }

    @GetMapping("/async/report/completable")
    public CompletableFuture<String> reportCompletable(@RequestParam(defaultValue = "sales") String name) {
        return asyncService.generateReportCompletable(name);
    }

    @GetMapping("/async/report/combined")
    public CompletableFuture<Map<String, Object>> combined(@RequestParam(defaultValue = "SKU-1") String sku,
                                                          @RequestParam(defaultValue = "false") boolean failInventory) {

        CompletableFuture<BigDecimal> priceF = asyncService.fetchPrice(sku)
                .whenComplete((v, ex) -> {
                    if (ex == null) log.info("price ok");
                    else log.info("price fail");
                })
                .exceptionally(ex -> new BigDecimal("0.00"));

        CompletableFuture<Integer> invF =  asyncService.fetchInventory(sku)
                .whenComplete((v, ex) -> {
                    if (ex == null) log.info("inventory ok");
                    else log.info("inventory fail");
                })
                .exceptionally(ex -> Integer.valueOf(0));

        CompletableFuture<Integer> etaF = asyncService.fetchShippingEtaDays(sku)
                .whenComplete((v, ex) -> {
                    if (ex == null) log.info("eta ok");
                    else log.info("eta fail");
                })
                .exceptionally(ex -> -1);

        return CompletableFuture.allOf(priceF, invF, etaF)
                .thenApply(ignored -> Map.of(
                        "sku", sku,
                        "price", priceF.join(),
                        "inventory", invF.join(),
                        "etaDays", etaF.join()
                ));
    }

    @GetMapping("/async/report/sequential")
    public Map<String, Object> sequential(@RequestParam(defaultValue = "SKU-1") String sku) {
        AsyncService.QuoteData data = asyncService.fetchQuoteSequential(sku);
        return Map.of(
                "sku", data.sku(),
                "price", data.price(),
                "inventory", data.inventory(),
                "etaDays", data.etaDays()
        );
    }
}
