package com.example.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<SimpleLoggingFilter> loggingFilterRegistration() {
        FilterRegistrationBean<SimpleLoggingFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(new SimpleLoggingFilter());
        reg.addUrlPatterns("/filter/ping");
        reg.setOrder(1); // can adjust order if you have multiple filters
        return reg;
    }
}

