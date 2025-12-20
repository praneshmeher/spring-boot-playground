package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.example.transactional.Account;
import com.example.transactional.AccountRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner initAccounts(AccountRepository accountRepository) {
		return args -> {
			if (accountRepository.count() == 0) {
				accountRepository.save(new Account(null, "Alice", new BigDecimal("100.00")));
				accountRepository.save(new Account(null, "Bob", new BigDecimal("50.00")));
			}
		};
	}

}
