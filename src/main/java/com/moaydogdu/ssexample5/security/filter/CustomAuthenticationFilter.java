package com.moaydogdu.ssexample5.security.filter;


import com.moaydogdu.ssexample5.security.authentication.CustomAuthentication;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {



        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        var customAuthentication = new CustomAuthentication(authorization,null);

        try {
            Authentication authentication = authenticationManager.authenticate(customAuthentication);

            if (authentication.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            }
        } catch (AuthenticationException exception){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }


    }
}
