package com.login.loginpage.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class LoginSecurity {
    @Bean
public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails Siddique= User.builder()
                .username("Siddique")
                .password("{noop}admin123")
                .roles("Employee")
                .build();
        UserDetails Muzammil= User.builder()
                .username("Muzammil")
                .password("{noop}admin123")
                .roles("Manager")
                .build();
        UserDetails Fathima= User.builder()
                .username("Fathima")
                .password("{noop}admin123")
                .roles("Manager")
                .build();
        return new InMemoryUserDetailsManager(Siddique,Muzammil,Fathima);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .anyRequest().authenticated()
                )
                .formLogin(form->
                        form
                                .loginPage("/showLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                        )
                .logout(logout->logout.permitAll());
        return http.build();
    }
}
