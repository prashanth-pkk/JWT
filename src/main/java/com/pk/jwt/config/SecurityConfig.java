package com.pk.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/**")  // Ensure the security filter chain applies to all requests
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()  // Allow login endpoint without JWT
                .anyRequest().authenticated()  // Secure all other endpoints
                .and()
                .csrf(csrf -> csrf.disable())  // Disables CSRF for stateless applications
                .addFilter(new JwtAuthenticationFilter(authenticationManager()));  // Add JWT filter
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}
