package com.example.auth.security;

import com.example.auth.security.utils.ValidationsLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

   @Autowired
   private UserAuthenticationFilter userAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers(ValidationsLogin.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                .requestMatchers(ValidationsLogin.ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                .requestMatchers(ValidationsLogin.ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR")
                .requestMatchers(ValidationsLogin.ENDPOINTS_CUSTOMER).hasRole("CUSTOMER")
                .anyRequest().authenticated()
                .and().addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




}
