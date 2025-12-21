package com.example.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class PrefixResponseAspect {

    @Around("@annotation(com.example.annotation.PrefixResponse)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        PrefixResponse ann = method.getAnnotation(PrefixResponse.class);

        String prefix = ann.prefix() == null ? "" : ann.prefix();
        boolean apply = ann.apply();

        Object result = pjp.proceed();
        if (apply && result instanceof CharSequence cs) {
            return prefix + cs;
        }

        return result;
    }
}
