package com.moaydogdu.ssexample5.config;

import com.moaydogdu.ssexample5.security.filter.CustomAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class CustomWebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationFilter customAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .addFilterAt(
                        customAuthenticationFilter, BasicAuthenticationFilter.class)


                .authorizeRequests()
                .anyRequest()
                .permitAll();

        return http.build();
    }

}
