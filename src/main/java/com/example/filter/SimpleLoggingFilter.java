package com.example.filter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SimpleLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(SimpleLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        DispatcherType dt = request.getDispatcherType();

        log.info("[SimpleLoggingFilter] START {} {} dispatch={} remote={}", request.getMethod(), request.getRequestURI(), dt, request.getRemoteAddr());

        chain.doFilter(req, res);

        log.info("[SimpleLoggingFilter] FINISH {} {} status={}", request.getMethod(), request.getRequestURI(), response.getStatus());

    }
}