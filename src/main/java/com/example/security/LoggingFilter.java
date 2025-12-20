// java
package com.example.security;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Slf4j
@Component
public class LoggingFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        DispatcherType dt = request.getDispatcherType();

        log.info("[Filter] START {} {} dispatch={} remote={}", request.getMethod(), request.getRequestURI(), dt, request.getRemoteAddr());

        chain.doFilter(req, res);

        log.info("[Filter] FINISH {} {} status={}", request.getMethod(), request.getRequestURI(), response.getStatus());
    }

}
