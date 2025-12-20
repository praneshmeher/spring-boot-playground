package com.example.transactional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account addToBalance(Long id, BigDecimal amount) {
        int updated = accountRepository.addToBalance(id, amount);
        if (updated <= 0) {
            throw new IllegalArgumentException("account not found: " + id);
        }
        return accountRepository.findById(id).orElseThrow();
    }

    @Transactional
    public int resetAllBalances(BigDecimal newBalance) {
        return accountRepository.resetAllBalances(newBalance);
    }
}

