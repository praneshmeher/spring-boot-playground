package com.example.transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class TransferController {

    private final TransferService transferService;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public TransferController(TransferService transferService, AccountRepository accountRepository, AccountService accountService) {
        this.transferService = transferService;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Long from, @RequestParam Long to, @RequestParam String amount) {
        transferService.transfer(from, to, new BigDecimal(amount));
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/transfer-fail")
    public ResponseEntity<String> transferFail(@RequestParam Long from, @RequestParam Long to, @RequestParam String amount) {
        try {
            transferService.transferAndFail(from, to, new BigDecimal(amount));
            return ResponseEntity.ok("ok");
        } catch (RuntimeException ex) {
            // Transaction should be rolled back; return 500 so caller sees the failure
            return ResponseEntity.status(500).body("failed: " + ex.getMessage());
        }
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return accountRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // New endpoint: add amount to an account using the @Modifying query
    @PostMapping("/accounts/{id}/add")
    public ResponseEntity<Account> addToBalance(@PathVariable Long id, @RequestParam String amount) {
        Account updated = accountService.addToBalance(id, new BigDecimal(amount));
        return ResponseEntity.ok(updated);
    }

    // New endpoint: reset all balances to a given value using the @Modifying query
    @PostMapping("/accounts/reset")
    public ResponseEntity<String> resetAllBalances(@RequestParam String newBalance) {
        int updatedCount = accountService.resetAllBalances(new BigDecimal(newBalance));
        return ResponseEntity.ok("updated=" + updatedCount);
    }
}
