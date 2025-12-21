package com.example.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@SuppressWarnings("unused")
public class UppercaseResponseAspect {

    @Around("execution(String *(..)) && @annotation(com.example.annotation.UppercaseResponse)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        if (result == null) {
            return null;
        }
        return ((String) result).toUpperCase();
    }

}
