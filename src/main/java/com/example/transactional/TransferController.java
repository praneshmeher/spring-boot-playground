package com.example.transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class TransferController {

    private final TransferService transferService;
    private final AccountRepository accountRepository;

    public TransferController(TransferService transferService, AccountRepository accountRepository) {
        this.transferService = transferService;
        this.accountRepository = accountRepository;
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
}
