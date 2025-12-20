package com.example.transactional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transfer(Long fromId, Long toId, BigDecimal amount) {
        Account from = accountRepository.findById(fromId)
                .orElseThrow(() -> new IllegalArgumentException("from account not found"));
        Account to = accountRepository.findById(toId)
                .orElseThrow(() -> new IllegalArgumentException("to account not found"));

        if (from.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("insufficient funds");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);
    }

    @Transactional
    public void transferAndFail(Long fromId, Long toId, BigDecimal amount) {
        // perform the transfer then throw a runtime exception to trigger rollback
        transfer(fromId, toId, amount);
        throw new RuntimeException("simulated failure after transfer");
    }
}

