package com.example.transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // Add amount to the account's balance (uses @Modifying for update queries)
    @Modifying
    @Transactional
    @Query("update Account a set a.balance = a.balance + :amount where a.id = :id")
    int addToBalance(@Param("id") Long id, @Param("amount") BigDecimal amount);

    // Reset all account balances to the provided value (bulk update)
    @Modifying
    @Transactional
    @Query("update Account a set a.balance = :newBalance")
    int resetAllBalances(@Param("newBalance") BigDecimal newBalance);
}
