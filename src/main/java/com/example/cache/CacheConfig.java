package com.example.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class CacheConfig {

    @Bean("simpleKeyGen")
    public KeyGenerator simpleKeyGen() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                if (params == null || params.length == 0) {
                    return "no-args";
                }
                return params[0];
            }
        };
    }
}
