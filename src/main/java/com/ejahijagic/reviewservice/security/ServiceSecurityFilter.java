package com.ejahijagic.reviewservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
public class ServiceSecurityFilter extends GenericFilterBean {

    private final ServiceAuthenticationManager serviceAuthenticationManager;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var token = parseServiceToken(servletRequest);

        if (token != null && serviceAuthenticationManager.verifyServiceToken(token)) {
            var id = serviceAuthenticationManager.parseServiceName(token);
            var authorities = List.of(new SimpleGrantedAuthority("SERVICE_ROLE"));
            var authentication = new UsernamePasswordAuthenticationToken(id, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String parseServiceToken(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getHeader("X-Service-Token");
    }
}