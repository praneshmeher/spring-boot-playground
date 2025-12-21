package com.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public class SimpleLoggingInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(SimpleLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String user = request.getHeader("X-Request-User");
        if (user == null || user.isBlank()) {
            log.warn("[SimpleLoggingInterceptor] missing X-Request-User header for {} {}", request.getMethod(), request.getRequestURI());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing X-Request-User header");
            return false;
        }
        log.info("[SimpleLoggingInterceptor] user={} {} {}", user, request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("[SimpleLoggingInterceptor] postHandle {} {}", request.getMethod(), request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("[SimpleLoggingInterceptor] afterCompletion {} {}", request.getMethod(), request.getRequestURI());
    }
}
