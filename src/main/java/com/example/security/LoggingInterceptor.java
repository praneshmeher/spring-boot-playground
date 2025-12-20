// java
package com.example.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("[Interceptor] PRE  {} {} -> handler={}", request.getMethod(), request.getRequestURI(), handlerName(handler));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("[Interceptor] POST {} {} -> status={} handler={}", request.getMethod(), request.getRequestURI(), response.getStatus(), handlerName(handler));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("[Interceptor] EXIT {} {} -> status={} handler={} exception={}", request.getMethod(), request.getRequestURI(), response.getStatus(), handlerName(handler), ex == null ? "none" : ex.getMessage());
    }

    private String handlerName(Object handler) {
        if (handler instanceof HandlerMethod) {
            return ((HandlerMethod) handler).getMethod().getName();
        }
        return handler != null ? handler.getClass().getSimpleName() : "null";
    }
}
