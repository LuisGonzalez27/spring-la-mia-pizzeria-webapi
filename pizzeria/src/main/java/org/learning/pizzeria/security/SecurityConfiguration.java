package org.learning.pizzeria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    UserDetailsService userDetailsService(){
        return new DatabaseUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /*
    * home "/" USER, ADMIN
    * pizze "pizze" USER, ADMIN
    * create/edit "/pizze/create" , "/pizze/edit/{id}" ADMIN
    * dettaglio "pizze/{id)" USER, ADMIN
    * offerte "/offerte/..." ADMIN
     */

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/pizze/create", "/pizze/edit/**", "/pizze/delete/**")
                .hasAnyAuthority("ADMIN")
                .requestMatchers("/offerte/**")
                .hasAnyAuthority("ADMIN")
                .requestMatchers("/pizze", "pizze/**")
                .hasAnyAuthority("ADMIN" , "USER")
                .requestMatchers(HttpMethod.POST, "/pizze/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling();
        http.csrf().disable();
        return http.build();
    }

}