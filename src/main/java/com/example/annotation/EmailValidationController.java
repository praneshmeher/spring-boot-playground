package com.example.annotation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/validation")
public class EmailValidationController {

    @GetMapping("/email")
    public ResponseEntity<String> validateEmail(@RequestParam("email") @ValidEmail String email) {
        return ResponseEntity.ok("email valid: " + email);
    }
}

